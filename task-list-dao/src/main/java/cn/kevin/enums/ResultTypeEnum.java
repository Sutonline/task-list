package cn.kevin.enums;

/**
 * \
 * created by yongkang.zhang
 * added at 2017/11/30
 */
public enum ResultTypeEnum {

    SUCCESS("1"), ERROR("0");

    private String code;

    ResultTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
