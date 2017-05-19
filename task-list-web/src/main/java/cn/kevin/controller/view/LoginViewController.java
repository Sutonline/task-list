package cn.kevin.controller.view;

import cn.kevin.util.Constants;
import cn.kevin.util.ThreadLocalHelper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.kevin.service.LoginService;
/**
 * Created by yongkang.zhang on 2017/5/19.
 */
@Controller
@RequestMapping(value = "/login")
@Slf4j
public class LoginViewController {

    @Autowired
    private LoginService loginService;
    

    @GetMapping(value = "/index")
    public String index() {
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Param(value = "name") String name, @Param(value = "password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(password)) {
            String message = "用户名或密码为空，不能登录";
            log.error(message);
            modelAndView.addObject(Constants.TIP_MESSAGE, message);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        // 根据username去获取，然后进行
        boolean login = loginService.login(name, password);
        if (login) {
            ThreadLocalHelper.set(name);
            modelAndView.setViewName("redirect:/taskList/list");
        } else {
            modelAndView.addObject(Constants.TIP_MESSAGE, "用户名或密码错误");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }
}
