package top.kylewang.mapper;

import org.springframework.stereotype.Component;
import top.kylewang.pojo.Permission;

import java.util.List;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:21
 */
@Component("permissionMapper")
public interface PermissionMapper {
    List<Permission> getPermissions();

    Permission getPermissionByname(String permissionname);

    void addpermission(String permissionname);

    void deletepermission(int pid);

    void deleteRole_permission(int permissionid);
}
