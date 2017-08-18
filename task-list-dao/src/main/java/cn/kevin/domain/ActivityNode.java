package cn.kevin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ActivityNode {

    /**
     * nodeId
     */
    private Integer nodeId;

    /**
     * activityId
     */
    private Integer activityId;

    /**
     * 类型，目前是空
     */
    private Integer type;

    private Float sortNo;

    @NotNull(message = "content不能为空")
    private String content;

    private Integer status;

    private String remark;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueTime;

    private Integer warnCnt;

    @NotNull(message = "需要天数不能为空")
    @Min(value = 1, message = "最小天数为1")
    private Integer needDays;

}