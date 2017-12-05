package cn.kevin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"id"})
public class AppPlanHistory {

    private Integer id;

    @JsonProperty(value = "planId")
    private Long planId;

    @JsonProperty(value = "checkDate")
    private Date checkDate;

    @JsonProperty(value = "createTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}