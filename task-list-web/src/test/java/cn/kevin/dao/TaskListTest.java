package cn.kevin.dao;

import cn.kevin.domain.TaskList;
import cn.kevin.domain.page.PageRequest;
import cn.kevin.domain.query.TaskListQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 用来测试mybatis生成的代码是否可用
 * Created by yongkang.zhang on 2017/5/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "classpath:spring-config-test.xml"})
@Slf4j
public class TaskListTest {

    @Autowired
    private TaskListMapper taskListMapper;

    @Test
    public void testInsert() {
        TaskList taskList = new TaskList();
        taskList.setContent("xxxx");
        taskList.setState("02");
        //taskListMapper.insert(taskList);
        throw new RuntimeException("测试异常");
    }

    @Test
    public void testSelect() {
        final List<TaskList> taskLists = taskListMapper.selectAll();
        taskLists.forEach(taskList -> System.out.println(taskList));
    }

    @Test
    public void deleteById() {
        taskListMapper.deleteByPrimaryKey(3);
    }

    @Test
    public void update() {
        TaskList taskList = taskListMapper.selectByPrimaryKey(1);
        taskList.setRemark("xxxx");
        taskListMapper.updateByPrimaryKey(taskList);
    }


    @Test
    public void testListByPage() {
        TaskListQuery query = new TaskListQuery();
        query.setState("1");
        PageRequest pageRequest = new PageRequest(1, 5);
        List<TaskList> taskLists = taskListMapper.listByPage(query, pageRequest);
        log.info("查询结果是:\n{}", taskLists);
    }


}
