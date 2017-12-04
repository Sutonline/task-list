package cn.kevin.domain.query;

import lombok.Data;

import java.util.Date;

/**
 * 查询实体
 * created by yongkang.zhang
 * added at 2017/11/30
 */
@Data
public class AppPlanQuery {

    private Long planId;
    private Integer status;
    private Date checkDate;

}
