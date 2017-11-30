package cn.kevin.enums;

/**
 * 计划状态
 * created by yongkang.zhang
 * added at 2017/11/30
 */
public enum  AppPlanStatusEnum {

    NOT_START(0, "停止"), EXECUTING(1, "执行中");

    private Integer code;
    private String desc;

    AppPlanStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
