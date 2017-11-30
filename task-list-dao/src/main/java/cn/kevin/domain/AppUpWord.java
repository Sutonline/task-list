package cn.kevin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AppUpWord {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "word")
    private String word;

    @JsonProperty(value = "createTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonProperty(value = "status")
    private Integer status;

}