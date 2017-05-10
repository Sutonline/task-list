package cn.kevin.domain;

import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@ToString
public class TaskList {
    private Integer id;

    private String content;

    private String state;

    private Date createDate;

    private Date updateDate;

    private String remark;

    public TaskList() {
    }

    public TaskList(Integer id, String content, String state, Date createDate, Date updateDate, String remark) {
        this.id = id;
        this.content = content;
        this.state = state;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getState() {
        return state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public String getRemark() {
        return remark;
    }
}