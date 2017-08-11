package cn.kevin.service.impl;

import cn.kevin.dao.ActivityNodeMapper;
import cn.kevin.domain.ActivityNode;
import cn.kevin.domain.query.ActivityNodeQuery;
import cn.kevin.service.ActivityNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * activity service实现类
 * Created by yongkang.zhang on 2017/8/11.
 */
@Service
public class ActivityNodeServiceImpl implements ActivityNodeService {

    private ActivityNodeMapper activityNodeMapper;

    @Autowired
    public ActivityNodeServiceImpl(ActivityNodeMapper activityNodeMapper) {
        this.activityNodeMapper = activityNodeMapper;
    }

    @Override
    public int insert(ActivityNode activityNode) {
        return activityNodeMapper.insert(activityNode);
    }

    @Override
    public int update(ActivityNode activityNode) {
        return activityNodeMapper.updateByPrimaryKey(activityNode);
    }

    @Override
    public ActivityNode getByNodeId(Integer nodeId) {
        return activityNodeMapper.selectByPrimaryKey(nodeId);
    }

    @Override
    public List<ActivityNode> listByQuery(ActivityNodeQuery activityNodeQuery) {
        return activityNodeMapper.listByQuery(activityNodeQuery);
    }
}
