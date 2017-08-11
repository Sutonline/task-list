package cn.kevin.service.impl;

import cn.kevin.dao.ActivityMapper;
import cn.kevin.dao.ActivityNodeMapper;
import cn.kevin.domain.Activity;
import cn.kevin.domain.ActivityNode;
import cn.kevin.domain.query.ActivityNodeQuery;
import cn.kevin.domain.query.ActivityQuery;
import cn.kevin.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * activityService
 * Created by yongkang.zhang on 2017/8/11.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private ActivityMapper activityMapper;
    private ActivityNodeMapper activityNodeMapper;

    @Autowired
    public ActivityServiceImpl(ActivityMapper activityMapper, ActivityNodeMapper activityNodeMapper) {
        this.activityMapper = activityMapper;
        this.activityNodeMapper = activityNodeMapper;
    }

    @Override
    public int insert(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public int update(Activity activity) {
        return activityMapper.updateByPrimaryKey(activity);
    }

    @Override
    public Activity getByActivityId(Integer activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public List<Activity> listByQuery(ActivityQuery activityQuery) {
        return activityMapper.listByQuery(activityQuery);
    }

    @Override
    public Activity getActivityAndNoes(Integer activityId) {
        Activity activity = getByActivityId(activityId);
        if (activity != null) {
            ActivityNodeQuery query = new ActivityNodeQuery();
            query.setActivityId(activityId);
            query.setOrderBy("sort_no asc");
            List<ActivityNode> activityNodes = activityNodeMapper.listByQuery(query);
            activity.setActivityNodeList(activityNodes);
        }
        return activity;
    }
}
