package cn.kevin.domain.dto;

import cn.kevin.domain.AppPlan;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yongkang.zhang
 * Created by yongkang.zhang on 2017/11/21.
 */
@Data
@AllArgsConstructor
public class PlanStatistics {

    /**
     * 计划id
     */
    private Long planId;

    /**
     * plan实体
     */
    private AppPlan plan;

    /**
     * 坚持天数
     */
    private Integer keepDays;
}
