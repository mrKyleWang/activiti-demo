package top.kylewang.mapper;

import top.kylewang.po.User;



public interface LoginMapper {
	User getpwdbyname(String name);
}
