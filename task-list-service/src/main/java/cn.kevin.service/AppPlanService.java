package cn.kevin.service;

import cn.kevin.domain.AppPlan;

import java.util.List;

/**
 * app plan 服务
 * created by yongkang.zhang
 * added at 2017/11/30
 */
public interface AppPlanService {

    /**
     * change plan
     * @param planId 计划id
     * @return 变更结果
     */
    Boolean changePlan(Long planId);

    /**
     * 放弃
     * @param planId planId
     * @return 放弃结果
     */
    Boolean giveUp(Long planId);

    /**
     * 更新
     * @param plan 计划
     * @return 更新结果
     */
    Boolean update(AppPlan plan);


    /**
     * 查询最近的计划
     * @return 最近的计划
     */
    List<AppPlan> listRecent();

    /**
     * 删除最近的计划
     * @return 删除结果
     */
    Boolean deleteRecent();

    List<AppPlan> listAll();

}
