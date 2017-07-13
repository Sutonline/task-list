package cn.kevin.helper;

import java.lang.annotation.*;

/**
 * 用来标记哪些是需要增加跨域的访问
 * Created by yongkang.zhang on 2017/7/12.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WrapperResponseBody {

}
