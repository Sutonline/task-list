package cn.kevin.dao;

import cn.kevin.domain.TaskLabel;
import java.util.List;

public interface TaskLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskLabel record);

    TaskLabel selectByPrimaryKey(Integer id);

    List<TaskLabel> selectAll();

    int updateByPrimaryKey(TaskLabel record);
}