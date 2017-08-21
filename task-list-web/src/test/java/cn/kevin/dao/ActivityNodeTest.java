package cn.kevin.dao;

import cn.kevin.domain.ActivityNode;
import cn.kevin.domain.page.PageRequest;
import cn.kevin.domain.query.ActivityNodeQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

    @Test
    public void testListByQuery() {
        ActivityNodeQuery activityNodeQuery = new ActivityNodeQuery();
        activityNodeQuery.setActivityId(8);
        activityNodeQuery.setSortNo(0.0f);
        activityNodeQuery.setOrderBy(" sort_no asc");
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(1);
        pageRequest.setPageSize(1);
        List<ActivityNode> activityNodes = activityNodeMapper.listByQuery(activityNodeQuery, pageRequest);
        log.info("查询结果是: {}", activityNodes);
    }

    @Test
    public void testUpdate() {
        ActivityNode activityNode = activityNodeMapper.selectByPrimaryKey(15);
        activityNode.setStatus(1);
        activityNodeMapper.updateByPrimaryKey(activityNode);
    }

    @Autowired
    public void setActivityNodeMapper(ActivityNodeMapper activityNodeMapper) {
        this.activityNodeMapper = activityNodeMapper;
    }
}
