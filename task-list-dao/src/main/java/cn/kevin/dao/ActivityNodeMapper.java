package cn.kevin.dao;

import cn.kevin.domain.ActivityNode;
import cn.kevin.domain.page.PageRequest;
import cn.kevin.domain.query.ActivityNodeQuery;

import java.util.List;

public interface ActivityNodeMapper {

    int deleteByPrimaryKey(Integer nodeId);

    int insert(ActivityNode record);

    ActivityNode selectByPrimaryKey(Integer nodeId);

    List<ActivityNode> selectAll();

    int updateByPrimaryKey(ActivityNode record);

    List<ActivityNode> listByQuery(ActivityNodeQuery query, PageRequest pageRequest);

    int completedNode(Integer nodeId);
}