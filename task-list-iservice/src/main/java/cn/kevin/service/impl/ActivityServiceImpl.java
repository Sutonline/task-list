package cn.kevin.service.impl;

import cn.kevin.dao.ActivityMapper;
import cn.kevin.dao.ActivityNodeMapper;
import cn.kevin.domain.Activity;
import cn.kevin.domain.ActivityNode;
import cn.kevin.domain.page.PageRequest;
import cn.kevin.domain.query.ActivityNodeQuery;
import cn.kevin.domain.query.ActivityQuery;
import cn.kevin.enums.ActivityNodeStatusEnum;
import cn.kevin.enums.ActivityStatusEnum;
import cn.kevin.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static cn.kevin.enums.ActivityNodeStatusEnum.fromStringCode;

/**
 * activityService
 * Created by yongkang.zhang on 2017/8/11.
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    private ActivityMapper activityMapper;
    private ActivityNodeMapper activityNodeMapper;

    @Autowired
    public ActivityServiceImpl(ActivityMapper activityMapper, ActivityNodeMapper activityNodeMapper) {
        this.activityMapper = activityMapper;
        this.activityNodeMapper = activityNodeMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(Activity activity) {
        int cnt;
        try {
            cnt = activityMapper.insert(activity);
            List<ActivityNode> activityNodeList = activity.getActivityNodeList();
            if (activityNodeList != null && ! activityNodeList.isEmpty()) {
                activityNodeList.forEach(node -> {
                    node.setActivityId(activity.getActivityId());
                    activityNodeMapper.insert(node);
                });
            }
        } catch (Exception e) {
            log.error("保存activity[{}]出错", activity, e);
            throw new RuntimeException(e);
        }
        return cnt;
    }

    @Override
    @Transactional
    public int update(Activity activity) {
        try {
            int cnt = activityMapper.updateByPrimaryKey(activity);
            List<ActivityNode> activityNodeList = activity.getActivityNodeList();
            // 如果列表为空，那么删除所有activityNode的数据
            Integer activityId = activity.getActivityId();
            if (activityNodeList == null || activityNodeList.isEmpty()) {
                activityNodeMapper.deleteByActivityId(activityId);
            } else {
                ActivityNode node;
                for (int i = 0; i < activityNodeList.size(); i++) {
                    node = activityNodeList.get(i);
                    boolean newAdd = node.getStatus() == null || node.getStatus() == -1;
                    if (newAdd || Objects.equals(node.getStatus(), ActivityNodeStatusEnum.NOT_RUN_YET.getCode())) {
                        node.setSortNo((float) i);
                        node.setActivityId(activityId);
                        node.setStatus(ActivityNodeStatusEnum.NOT_RUN_YET.getCode());
                        node.setWarnCnt(3);
                        if (newAdd) {
                            node.setCreateTime(new Date());
                            activityNodeMapper.insert(node);
                        } else {
                            activityNodeMapper.updateByPrimaryKey(node);
                        }
                    }
                }
            }
            return cnt;
        } catch (Exception e) {
            log.error("更新activity[{}]信息失败", activity, e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public Activity getByActivityId(Integer activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public List<Activity> listByQuery(ActivityQuery activityQuery) {
        List<Activity> list = activityMapper.listByQuery(activityQuery);
        if (list == null) return null;
        for (Activity activity : list) {
            int totalCnt = 0;
            int unCompletedCnt = 0;
            int completedCnt = 0;
            ActivityNodeQuery activityNodeQuery = new ActivityNodeQuery();
            activityNodeQuery.setActivityId(activity.getActivityId());
            List<ActivityNode> activityNodeList = activityNodeMapper.listByQuery(activityNodeQuery, null);
            if (activityNodeList != null) {
                for (ActivityNode node : activityNodeList) {
                    totalCnt += 1;
                    Integer status = node.getStatus();
                    if (status == null) { continue; }
                    switch (fromStringCode(status)) {
                        case NOT_RUN_YET:
                            unCompletedCnt += 1;
                            break;
                        case RUNNING:
                            activity.setCurrentNodeName(node.getContent());
                            activity.setCurrentNodeId(node.getNodeId());
                            activity.setCurrentDueTime(node.getDueTime());
                            unCompletedCnt += 1;
                            break;
                        case FINISHED:
                            completedCnt += 1;
                            break;
                        case DELETED:
                            totalCnt -= 1;
                            break;
                        default:
                            break;
                    }
                }
            }
            activity.setTaskTotalCnt(totalCnt);
            activity.setTaskUncompletedCnt(unCompletedCnt);
            activity.setTaskCompletedPercent(((double) Math.round(((double) completedCnt / totalCnt) * 100.0)));
            activity.setActivityNodeList(activityNodeList);
        }

        return list;
    }

    @Override
    public Activity getActivityAndNoes(Integer activityId) {
        Activity activity = getByActivityId(activityId);
        if (activity != null) {
            ActivityNodeQuery query = new ActivityNodeQuery();
            query.setActivityId(activityId);
            query.setOrderBy("sort_no asc");
            List<ActivityNode> activityNodes = activityNodeMapper.listByQuery(query, null);
            activity.setActivityNodeList(activityNodes);
        }
        return activity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void completeActivityNode(Integer nodeId) {
        try {
            // 1. 完成当前任务
            activityNodeMapper.completedNode(nodeId);

            // 2.更新涉及的node及activity
            ActivityNode udpNode = activityNodeMapper.selectByPrimaryKey(nodeId);
            Integer activityId = udpNode.getActivityId();
            ActivityNodeQuery activityNodeQuery = new ActivityNodeQuery();
            activityNodeQuery.setActivityId(activityId);
            activityNodeQuery.setSortNo(udpNode.getSortNo());
            activityNodeQuery.setOrderBy(" sort_no asc");
            PageRequest pageRequest = new PageRequest();
            pageRequest.setPageIndex(1);
            pageRequest.setPageSize(1);
            List<ActivityNode> activityNodes = activityNodeMapper.listByQuery(activityNodeQuery, pageRequest);
            if (activityNodes == null || activityNodes.isEmpty()) { //如果是最后一个任务就完成整个activity
                Activity activity = activityMapper.selectByPrimaryKey(activityId);
                activity.setStatus(ActivityStatusEnum.DONE.getCode());
                activityMapper.updateByPrimaryKey(activity);
            } else { //否则启用下一个任务为进行中
                ActivityNode activityNode = activityNodes.get(0);
                activityNode.setStatus(ActivityNodeStatusEnum.RUNNING.getCode());
                activityNode.setDueTime(new LocalDate(new Date()).plusDays(activityNode.getNeedDays()).toDate());
                activityNodeMapper.updateByPrimaryKey(activityNode);
            }
        } catch (Exception e) {
            log.error("完成任务异常", e);
            throw new RuntimeException("完成任务异常，异常信息是： " + e);
        }
    }


    @Override
    public void addCreateInformation(Activity activity) {
        activity.setCreateTime(new Date());
        activity.setStatus(ActivityStatusEnum.EXECUTING.getCode());
        List<ActivityNode> activityNodeList = activity.getActivityNodeList();
        for (int i = 0; i < activityNodeList.size(); i++) {
            ActivityNode node = activityNodeList.get(i);
            if (i == 0) {
                node.setStatus(ActivityNodeStatusEnum.RUNNING.getCode());
                LocalDate localDate = new LocalDate(new Date());
                LocalDate dueDate = localDate.plusDays(node.getNeedDays());
                node.setDueTime(dueDate.toDate());
            } else {
                node.setStatus(ActivityNodeStatusEnum.NOT_RUN_YET.getCode());
            }
            node.setCreateTime(new Date());
            node.setSortNo((float) i);
            node.setWarnCnt(0);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteActivityAndNode(Integer activityId) {
        activityMapper.deleteByPrimaryKey(activityId);
        activityNodeMapper.deleteByActivityId(activityId);
    }
}
