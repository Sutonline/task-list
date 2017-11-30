package cn.kevin.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yongkang.zhang on 2017/7/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 0 错误
     * 1 成功
     */
    private String code;
    private String message;
    private Object T;

    public static Result create(String code, String message, Object t) {
        return new Result(code, message, t);
    }
}
