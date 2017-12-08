package cn.kevin.common.enums;

import java.util.Arrays;

/**
 * 阿里云的请求类型
 * created by yongkang.zhang
 * added at 2017/12/8
 */
public enum AliyunRequestTypeEnum {

    BASIC_INFO(1), BAND_WIDTH(2), ALL(3);

    private Integer code;

    AliyunRequestTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static AliyunRequestTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(AliyunRequestTypeEnum.values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }
}
