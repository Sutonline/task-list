package cn.kevin.helper;

import java.lang.annotation.*;

/**
 * 为response增加result包装
 * created by yongkang.zhang
 * added at 2017/11/30
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResultWrapper {

}
