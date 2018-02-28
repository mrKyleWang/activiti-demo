package top.kylewang.service.impl;


import top.kylewang.mapper.LoginMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.kylewang.pojo.User;
import top.kylewang.service.LoginService;

/**
 * @author Kyle.Wang
 * 2018-02-28 10:58
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 5)
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public String getpwdbyname(String name) {
        User s = loginMapper.getpwdbyname(name);
        if (s != null) {
            return s.getPassword();
        } else {
            return null;
        }
    }

}
