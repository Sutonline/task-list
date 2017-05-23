package cn.kevin.domain.query;

import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * Created by yongkang.zhang on 2017/5/23.
 */
public class TaskListQuery {

    private String label;
    private String content;
    private String state;
    private Date createDate;
    private Date updateDate;
    private String remark;
    private String orderByClause;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("label", label)
                .add("content", content)
                .add("state", state)
                .add("createDate", createDate)
                .add("updateDate", updateDate)
                .add("remark", remark)
                .add("orderByClause", orderByClause)
                .toString();
    }
}
