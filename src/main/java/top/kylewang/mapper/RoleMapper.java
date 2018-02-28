package top.kylewang.mapper;

import java.util.List;

import top.kylewang.po.Permission;
import top.kylewang.po.Role;
import top.kylewang.po.Role_permission;
import top.kylewang.po.User_role;


public interface RoleMapper {
	List<Role> getRoles();
	void adduserrole(User_role ur);
	Role getRoleidbyName(String rolename);
	List<Role> getRoleinfo();
	void addRole(Role role);
	void addRolePermission(Role_permission rp);
	void deleterole(int rid);
	void deleterole_permission(int roleid);
	void deleteuser_role(int roleid);
	Role getRolebyid(int rid);
}
