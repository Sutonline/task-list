package cn.kevin.controller.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yongkang.zhang on 2017/5/19.
 */
@RestController
@RequestMapping(value = "/login")
public class LoginRestController {

    @PostMapping(value = "/test")
    public String login() {
        return "";
    }
}
