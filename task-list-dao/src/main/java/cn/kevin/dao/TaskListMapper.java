package cn.kevin.dao;

import cn.kevin.domain.TaskList;
import java.util.List;

public interface TaskListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskList record);

    TaskList selectByPrimaryKey(Integer id);

    List<TaskList> selectAll();

    int updateByPrimaryKey(TaskList record);

    /**
     * 按状态查询任务
     * @param state
     * @return
     */
    List<TaskList> selectByState(String state);

}