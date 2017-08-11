package cn.kevin.dao;

import cn.kevin.domain.ActivityNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * actrivity node
 * Created by yongkang.zhang on 2017/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "classpath:spring-config-test.xml"})
@Slf4j
public class ActivityNodeTest {

    private ActivityNodeMapper activityNodeMapper;

    @Test
    public void testInsert() {
        ActivityNode activityNode = new ActivityNode();
        activityNode.setActivityId(1);
        activityNode.setNodeId(1);
        activityNodeMapper.insert(activityNode);
    }

    @Test
    public void testSelectAll() {
        activityNodeMapper.selectAll().forEach(activityNode -> log.info("查询结果是: \n{}", activityNode));
    }

    @Autowired
    public void setActivityNodeMapper(ActivityNodeMapper activityNodeMapper) {
        this.activityNodeMapper = activityNodeMapper;
    }
}
