package cn.kevin.service;

import cn.kevin.domain.ActivityNode;
import cn.kevin.domain.query.ActivityNodeQuery;

import java.util.List;

/**
 * 活动节点 service
 * Created by yongkang.zhang on 2017/8/11.
 */
public interface ActivityNodeService {

    int insert(ActivityNode activityNode);

    int update(ActivityNode activityNode);

    ActivityNode getByNodeId(Integer nodeId);

    List<ActivityNode> listByQuery(ActivityNodeQuery query);
}
