package cn.kevin.service.impl;

import cn.kevin.dao.AppPlanMapper;
import cn.kevin.domain.AppPlan;
import cn.kevin.domain.query.AppPlanQuery;
import cn.kevin.enums.AppPlanStatusEnum;
import cn.kevin.service.AppPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by yongkang.zhang
 * added at 2017/11/30
 */
@Service
public class AppPlanServiceImpl implements AppPlanService {

    private final AppPlanMapper mapper;

    @Autowired
    public AppPlanServiceImpl(AppPlanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean changePlan(Long planId) {
        // 查询执行中的计划
        AppPlanQuery query = new AppPlanQuery();
        query.setStatus(AppPlanStatusEnum.EXECUTING.getCode());
        List<AppPlan> appPlans = mapper.listByQuery(query);

        // 更新成停止状态
        appPlans.forEach(plan -> {
            plan.setStatus(AppPlanStatusEnum.NOT_START.getCode());
            mapper.update(plan);
        });

        // 取出要执行的plan并更新
        AppPlan appPlan = mapper.getByPlanId(planId);
        appPlan.setStatus(AppPlanStatusEnum.EXECUTING.getCode());
        mapper.update(appPlan);

        return Boolean.TRUE;
    }

    @Override
    public Boolean giveUp(Long planId) {
        AppPlan plan = mapper.getByPlanId(planId);
        plan.setStatus(AppPlanStatusEnum.NOT_START.getCode());
        plan.setGiveCnt((plan.getGiveCnt() == null ? 0 : plan.getGiveCnt()) + 1);
        plan.setKeepDays(0);
        plan.setSuccessUps(0);
        // 如果当前天数大于最长天数，那么就设置
        if (plan.getKeepDays() > plan.getLongestDays()) {
            plan.setLongestDays(plan.getKeepDays());
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean update(AppPlan plan) {
        mapper.update(plan);
        return Boolean.TRUE;
    }

    @Override
    public List<AppPlan> listRecent() {
        return mapper.selectLast();
    }

    @Override
    public Boolean deleteRecent() {
        mapper.updateLast();
        return Boolean.TRUE;
    }

    @Override
    public List<AppPlan> listAll() {
        return mapper.selectAll();
    }
}
