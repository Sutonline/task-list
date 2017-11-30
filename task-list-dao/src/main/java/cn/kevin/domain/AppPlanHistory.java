package cn.kevin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AppPlanHistory {

    @JsonProperty(value = "planId")
    private Long planId;

    @JsonProperty(value = "keepDays")
    private Integer keepDays;

    @JsonProperty(value = "createTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}