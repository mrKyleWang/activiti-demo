package top.kylewang.mapper;

import java.util.List;

import top.kylewang.po.User;

public interface UserMapper {
	List<User> getusers();
	User getUserByid(int id);
	void deleteuser(int uid);
	void deleteuserrole(int uid);
	void adduser(User user);
	void updateuser(User user);
	int getUidByusername(String username);
}
