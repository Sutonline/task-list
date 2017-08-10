package cn.kevin.enums;

/**
 * activity 状态枚举
 * Created by yongkang.zhang on 2017/8/10.
 */
public enum ActivityStatusEnum {
    EXECUTING(1), PAUSE(2), DONE(3), DELETE(4);

    private int code;

    ActivityStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
