package top.kylewang.mapper;

import org.springframework.stereotype.Component;
import top.kylewang.pojo.User;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:18
 */
@Component("loginMapper")
public interface LoginMapper {
    User getpwdbyname(String name);
}
