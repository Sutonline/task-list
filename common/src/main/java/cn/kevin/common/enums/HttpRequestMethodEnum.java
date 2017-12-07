package cn.kevin.common.enums;

/**
 * http请求方法枚举
 * created by yongkang.zhang
 * added at 2017/12/7
 */
public enum HttpRequestMethodEnum {
    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");


    private String code;

    HttpRequestMethodEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
