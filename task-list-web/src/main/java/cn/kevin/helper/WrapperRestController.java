package cn.kevin.helper;

import cn.kevin.common.Result;
import cn.kevin.enums.ResultTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yongkang.zhang on 2017/7/12.
 */
@RestControllerAdvice(basePackages = {"cn.kevin"})
@Slf4j
public class WrapperRestController implements ResponseBodyAdvice {

    /**
     * @param methodParameter 方法
     * @param aClass class
     * @return 是否支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.hasMethodAnnotation(ResponseResultWrapper.class)
                || (methodParameter.getDeclaringClass().getAnnotation(ResponseResultWrapper.class) != null);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return Result.create(ResultTypeEnum.SUCCESS.getCode(), "成功", body);
    }

    @ExceptionHandler(value = {Exception.class})
    public Result catchException(Exception exception, HttpServletRequest request) {
        log.error("{}请求发生了错误", request.getRequestURL().toString(), exception);
        return Result.create(ResultTypeEnum.ERROR.getCode(), "发生错误", exception.getMessage());
    }
}
