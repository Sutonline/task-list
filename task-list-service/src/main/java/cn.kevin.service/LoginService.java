package cn.kevin.service;

import org.springframework.stereotype.Service;

/**
 * Created by yongkang.zhang on 2017/5/19.
 */
public interface LoginService {

    /**
     * 登录验证
     * @param name
     * @param password
     * @return
     */
    boolean login(String name, String password);
}
