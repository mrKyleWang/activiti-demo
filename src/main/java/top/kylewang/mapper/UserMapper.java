package top.kylewang.mapper;

import org.springframework.stereotype.Component;
import top.kylewang.pojo.User;

import java.util.List;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:21
 */
@Component("userMapper")
public interface UserMapper {
    List<User> getusers();

    User getUserByid(int id);

    void deleteuser(int uid);

    void deleteuserrole(int uid);

    void adduser(User user);

    void updateuser(User user);

    int getUidByusername(String username);
}
