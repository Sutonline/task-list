package cn.kevin.domain;

import cn.kevin.annotation.JsonNumberFormat;
import cn.kevin.annotation.NumberSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class Activity {

    private Integer activityId;

    @NotNull(message = "活动名称不能为空")
    private String activityName;

    private Integer status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String remark;

    /**
     * 正常的list结构就可以
     */
    private List<ActivityNode> activityNodeList;

    /************************************************冗余字段 方便展示**********************************************/

    /**
     * 总数
     */
    private Integer taskTotalCnt;

    /**
     * 未完成任务数
     */
    private Integer taskUncompletedCnt;

    /**
     * 完成任务百分比
     */
    @JsonNumberFormat(format = "##0.00'%'")
    @JsonSerialize(using = NumberSerializer.class)
    private Double taskCompletedPercent;

    /**
     * 当前nodeId
     */
    private Integer currentNodeId;

    /**
     * 当前任务名称
     */
    private String currentNodeName;

    /**
     * 当前时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date currentDueTime;

    /**
     * 下一个任务名称
     */
    private String nextNodeName;

}