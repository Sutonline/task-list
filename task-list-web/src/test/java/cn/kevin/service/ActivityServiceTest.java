package cn.kevin.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * activity service 单元测试
 * Created by yongkang.zhang on 2017/8/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "classpath:spring-config-test.xml"})
@Slf4j
public class ActivityServiceTest {

    private ActivityService activityService;

    @Test
    public void testCompleteNode() {
        activityService.completeActivityNode(15);
    }


    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }
}
