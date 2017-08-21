package cn.kevin.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * json数字格式化
 * Created by yongkang.zhang on 2017/8/21.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonNumberFormat {
    String format();
}
