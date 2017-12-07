package cn.kevin.common.enums;

import java.util.Arrays;

/**
 * vultr 请求哪部分信息
 * created by yongkang.zhang
 * added at 2017/12/7
 */
public enum VultrRequestTypeEnum {

    BASIC_INFO(1), BAND_WIDTH(2), ALL(3);

    private Integer code;

    VultrRequestTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static VultrRequestTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(VultrRequestTypeEnum.values()).filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }

}
