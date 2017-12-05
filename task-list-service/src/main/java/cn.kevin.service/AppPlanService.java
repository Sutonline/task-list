package cn.kevin.service;

import cn.kevin.domain.AppPlan;
import cn.kevin.domain.AppPlanHistory;

import java.util.Date;
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

    /**
     * 获取当前计划
     * @return 当前计划
     */
    AppPlan getCurrent();

    /**
     * 检查date是否签到
     * @param planId 计划id
     * @param checkDate 日期
     * @return 是否签到
     */
    Boolean isCheck(Long planId, Date checkDate);

    /**
     * 签到
     * @param plan 计划
     * @return 签到结果
     */
    Boolean check(AppPlan plan);

    /**
     * 签到的日期
     * @param planId 计划id
     * @return 签到的日期
     */
    List<AppPlanHistory> listByPlanId(Long planId);
}
