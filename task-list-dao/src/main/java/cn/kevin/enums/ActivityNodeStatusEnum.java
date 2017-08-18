package cn.kevin.enums;

import java.util.Objects;

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


    public static ActivityNodeStatusEnum fromStringCode(Integer code) {
        if (code == null) return null;
        for (ActivityNodeStatusEnum nodeStatusEnum : ActivityNodeStatusEnum.values()) {
            if (Objects.equals(nodeStatusEnum.getCode(), code))  return nodeStatusEnum;
        }
        return null;
    }
}
