package cn.kevin.dao;

import cn.kevin.domain.TaskList;
import cn.kevin.domain.query.TaskListQuery;

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

    /**
     * 根据query实体进行查询List<TaskList>
     * @param taskListQuery
     * @return
     */
    List<TaskList> selectByTaskListQuery(TaskListQuery taskListQuery);
}