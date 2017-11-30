package cn.kevin.dao;

import cn.kevin.domain.AppPlan;
import cn.kevin.domain.query.AppPlanQuery;

import java.util.List;

public interface AppPlanMapper {

    int insert(AppPlan record);

    List<AppPlan> selectAll();

    List<AppPlan> selectLast();

    void updateLast();

    void update(AppPlan plan);

    AppPlan getByPlanId(Long planId);

    List<AppPlan> listByQuery(AppPlanQuery query);
}