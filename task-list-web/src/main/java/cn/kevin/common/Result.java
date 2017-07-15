package cn.kevin.common;

import lombok.Data;

/**
 * Created by yongkang.zhang on 2017/7/15.
 */
@Data
public class Result<T> {

    /**
     * 0 错误
     * 1 成功
     */
    private String code;
    private String message;
    private Object T;
}
