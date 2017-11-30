package cn.kevin.dao;

import cn.kevin.domain.AppPlan;
import cn.kevin.enums.AppPlanStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * app plan mappter 测试类
 * created by yongkang.zhang
 * added at 2017/11/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "classpath:spring-config-test.xml"})
@Slf4j
public class AppPlanMapperTest {

    @Autowired
    private AppPlanMapper appPlanMapper;

    @Test
    public void save() throws Exception {
        // 初始化list
        initList().forEach(plan -> appPlanMapper.insert(plan));

    }

    @Test
    public void update() throws Exception {
        AppPlan plan = appPlanMapper.getByPlanId(1L);
        plan.setKeepDays(1);
        appPlanMapper.update(plan);
    }



    private List<AppPlan> initList() {
        List<AppPlan> list = new ArrayList<>();

        // plan 1
        AppPlan plan1 = new AppPlan();
        plan1.setPlanId(1L);
        plan1.setName("健身");
        plan1.setDesc("啦啦啦");
        plan1.setStatus(AppPlanStatusEnum.NOT_START.getCode());
        list.add(plan1);

        // plan 2
        AppPlan plan2 = new AppPlan();
        plan2.setPlanId(2L);
        plan2.setName("阅读");
        plan2.setDesc("阅读");
        plan2.setStatus(AppPlanStatusEnum.NOT_START.getCode());
        list.add(plan2);

        // plan 3
        AppPlan plan3 = new AppPlan();
        plan3.setPlanId(3L);
        plan3.setName("禅");
        plan3.setDesc("啦啦啦");
        plan3.setStatus(AppPlanStatusEnum.NOT_START.getCode());
        list.add(plan3);

        return list;
    }
}
