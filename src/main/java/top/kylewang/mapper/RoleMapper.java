package top.kylewang.mapper;

import org.springframework.stereotype.Component;
import top.kylewang.pojo.Role;
import top.kylewang.pojo.Role_permission;
import top.kylewang.pojo.User_role;

import java.util.List;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:21
 */
@Component("roleMapper")
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
