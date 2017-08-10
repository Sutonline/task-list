package cn.kevin.enums;

/**
 * node节点执行状态枚举
 * Created by yongkang.zhang on 2017/8/10.
 */
public enum ActivityNodeStatusEnum {
    NOT_RUN_YET(0), RUNNING(1), FINISHED(2), DELETED(3);

    private int code;

    ActivityNodeStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
