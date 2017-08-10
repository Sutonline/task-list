package cn.kevin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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

    private Integer preNodeId;

    private Integer nextNodeId;

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

}