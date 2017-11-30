package cn.kevin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AppPlan {

    @JsonProperty(value = "planId")
    private Long planId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "desc")
    private String desc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonProperty(value = "status")
    private Integer status;

    @JsonProperty(value = "keepDays")
    private Integer keepDays;

    @JsonProperty(value = "giveCnt")
    private Integer giveCnt;

    @JsonProperty(value = "successUps")
    private Integer successUps;

    @JsonProperty(value = "longestDays")
    private Integer longestDays;

}