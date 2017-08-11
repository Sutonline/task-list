package cn.kevin.dao;

import cn.kevin.domain.Activity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * activity test
 * Created by yongkang.zhang on 2017/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "classpath:spring-config-test.xml"})
@Slf4j
public class ActivityTest {

    private ActivityMapper activityMapper;

    @Test
    public void testInsert() {
        Activity activity = new Activity();
        activity.setActivityId(1);
        activity.setActivityName("LALALA");
        activityMapper.insert(activity);
    }

    @Test
    public void testSelect() {
        List<Activity> activities = activityMapper.selectAll();
        activities.forEach(activity -> {
            log.info("activity的结果是: {}", activity);
        });
    }

    @Autowired
    public void setActivityMapper(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }
}
