package cn.kevin.dao;

import cn.kevin.domain.TaskLabel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yongkang.zhang on 2017/5/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "classpath:spring-config-test.xml"})
public class TaskLabelTest {

    @Resource
    private TaskLabelMapper taskLabelMapper;

    @Test
    public void test() {
        taskLabelMapper.deleteByPrimaryKey(1);
        taskLabelMapper.deleteByPrimaryKey(2);
        taskLabelMapper.deleteByPrimaryKey(3);
        TaskLabel label1 = new TaskLabel();
        label1.setName("work");
        label1.setValue("工作");
        taskLabelMapper.insert(label1);
        TaskLabel label2 = new TaskLabel();
        label2.setName("life");
        label2.setValue("生活");
        TaskLabel label3 = new TaskLabel();
        label3.setName("study");
        label3.setValue("学习");
        taskLabelMapper.insert(label2);
        taskLabelMapper.insert(label3);
    }

    @Test
    public void testQuery() {
        List<TaskLabel> taskLabels = taskLabelMapper.selectAll();
        taskLabels.forEach(taskLabel -> System.out.println(taskLabel.toString()));
    }

}
