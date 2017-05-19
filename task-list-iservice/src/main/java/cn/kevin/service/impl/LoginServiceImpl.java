package cn.kevin.service.impl;

import cn.kevin.dao.UserMapper;
import cn.kevin.domain.User;
import cn.kevin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yongkang.zhang on 2017/5/19.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String name, String password) {
        User queryUser = new User();
        queryUser.setName(name);
        List<User> users = userMapper.selectByUser(queryUser);
        if (users != null && users.size() == 1) {
            User user = users.get(0);
            if (passwordEncoder.matches(password, user.getPassword()))
                return true;
        }
        return false;
    }
}
