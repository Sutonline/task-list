package cn.kevin.dao;

import cn.kevin.domain.AppPlanHistory;
import java.util.List;

public interface AppPlanHistoryMapper {

    int deleteByPrimaryKey(Long planId);

    int insert(AppPlanHistory record);

    AppPlanHistory selectByPrimaryKey(Long planId);

    List<AppPlanHistory> selectAll();

    List<AppPlanHistory> listByPlanId(Long planId);
}