package top.kylewang.mapper;

import java.util.List;

import top.kylewang.po.Permission;

public interface PermissionMapper {
	List<Permission> getPermissions();
	Permission getPermissionByname(String permissionname);
	void addpermission(String permissionname);
	void deletepermission(int pid);
	void deleteRole_permission(int permissionid);
}
