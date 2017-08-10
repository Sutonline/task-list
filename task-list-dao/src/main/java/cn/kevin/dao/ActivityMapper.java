package cn.kevin.dao;

import cn.kevin.domain.Activity;
import java.util.List;

public interface ActivityMapper {

    int deleteByPrimaryKey(Integer activityId);

    int insert(Activity record);

    Activity selectByPrimaryKey(Integer activityId);

    List<Activity> selectAll();

    int updateByPrimaryKey(Activity record);
}