package cn.kevin.domain.query;

import lombok.Data;

import java.util.Date;

/**
 * Created by yongkang.zhang on 2017/8/11.
 */
@Data
public class ActivityNodeQuery {

    private int activityId;

    private Integer warnCnt;

    private Date dueTime;

    private String content;

    private Integer status;

    private String orderBy;
}
