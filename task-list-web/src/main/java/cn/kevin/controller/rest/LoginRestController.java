package cn.kevin.controller.rest;

import cn.kevin.common.Result;
import cn.kevin.helper.WrapperResponseBody;
import cn.kevin.service.LoginService;
import cn.kevin.util.Constants;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * login
 * Created by yongkang.zhang on 2017/5/19.
 */
@RestController
@RequestMapping(value = "/loginRest")
@Slf4j
@WrapperResponseBody
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/test")
    public String login() {
        return "";
    }

    @PostMapping(value = "/login")
    public Result<Boolean> login(@Param(value = "name") String name, @Param(value = "password") String password, HttpSession session) {
        Result<Boolean> result = new Result<>();
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(password)) {
            String message = "用户名或密码为空，不能登录";
            result.setCode("0");
            result.setMessage(message);
            result.setT(false);
        }
        // 根据username去获取，然后进行
        boolean login = loginService.login(name, password);
        if (login) {
            session.setAttribute(Constants.LOGIN_USER, name);
            result.setCode("1");
            result.setT(Boolean.TRUE);
        } else {
            result.setCode("0");
            result.setMessage("用户名或密码错误");
            result.setT(Boolean.FALSE);
        }

        return result;
    }

    @GetMapping(value = "/signOut")
    public Result<Boolean> loginOut(HttpSession session) {
        String attribute = (String) session.getAttribute(Constants.LOGIN_USER);
        if (! Strings.isNullOrEmpty(attribute)) {
            session.removeAttribute(Constants.LOGIN_USER);
        }
        Result<Boolean> result = new Result<>();
        result.setCode("1");
        result.setMessage("注销成功");
        result.setT(Boolean.FALSE);
        return result;
    }
}
