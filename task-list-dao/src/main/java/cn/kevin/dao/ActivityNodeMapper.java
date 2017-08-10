package cn.kevin.dao;

import cn.kevin.domain.ActivityNode;
import java.util.List;

public interface ActivityNodeMapper {
    int deleteByPrimaryKey(Integer nodeId);

    int insert(ActivityNode record);

    ActivityNode selectByPrimaryKey(Integer nodeId);

    List<ActivityNode> selectAll();

    int updateByPrimaryKey(ActivityNode record);
}