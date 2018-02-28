package top.kylewang.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.kylewang.mapper.PermissionMapper;
import top.kylewang.mapper.RoleMapper;
import top.kylewang.mapper.UserMapper;
import top.kylewang.pojo.*;
import top.kylewang.service.SystemService;

import java.util.List;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:22
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 5)
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<User> getallusers() {
        return userMapper.getusers();
    }

    @Override
    public List<User> getpageusers(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<User> l = userMapper.getusers();
        return l;
    }

    @Override
    public User getUserByid(int id) {
        User u = userMapper.getUserByid(id);
        return u;
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }

    @Override
    public void deleteuser(int uid) {
        userMapper.deleteuser(uid);
        userMapper.deleteuserrole(uid);
    }

    @Override
    public void adduser(User user, String[] rolenames) {
        userMapper.adduser(user);
        for (String rolename : rolenames) {
            Role role = roleMapper.getRoleidbyName(rolename);
            User_role ur = new User_role();
            ur.setRole(role);
            ur.setUser(user);
            roleMapper.adduserrole(ur);
        }
    }

    @Override
    public void adduser(User user) {
        userMapper.adduser(user);
    }

    @Override
    public void updateuser(int uid, User user, String[] rolenames) {
        if (rolenames == null) {
            user.setUid(uid);
            userMapper.updateuser(user);
            userMapper.deleteuserrole(uid);
        } else {
            user.setUid(uid);
            userMapper.updateuser(user);
            userMapper.deleteuserrole(uid);
            for (String rolename : rolenames) {
                Role role = roleMapper.getRoleidbyName(rolename);
                User_role ur = new User_role();
                ur.setRole(role);
                ur.setUser(user);
                roleMapper.adduserrole(ur);
            }
        }

    }

    @Override
    public List<Role> getpageRoleinfo(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<Role> l = roleMapper.getRoleinfo();
        return l;
    }

    @Override
    public List<Role> getRoleinfo() {
        return roleMapper.getRoleinfo();
    }

    @Override
    public List<Permission> getPermisions() {
        return permissionMapper.getPermissions();
    }

    @Override
    public void addrole(Role role, String[] permissionnames) {
        roleMapper.addRole(role);
        for (String permissionname : permissionnames) {
            Permission p = permissionMapper.getPermissionByname(permissionname);
            Role_permission rp = new Role_permission();
            rp.setRole(role);
            rp.setPermission(p);
            roleMapper.addRolePermission(rp);
        }
    }

    @Override
    public void deleterole(int rid) {
        roleMapper.deleterole(rid);
        roleMapper.deleterole_permission(rid);
        roleMapper.deleteuser_role(rid);
    }

    @Override
    public Role getRolebyid(int rid) {
        return roleMapper.getRolebyid(rid);
    }

    @Override
    public void deleterolepermission(int rid) {
        roleMapper.deleterole_permission(rid);
    }

    @Override
    public void updaterole(int rid, String[] permissionnames) {
        Role role = roleMapper.getRolebyid(rid);
        for (String permissionname : permissionnames) {
            Permission p = permissionMapper.getPermissionByname(permissionname);
            Role_permission rp = new Role_permission();
            rp.setRole(role);
            rp.setPermission(p);
            roleMapper.addRolePermission(rp);
        }
    }

    @Override
    public List<Permission> getPagePermisions(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        return permissionMapper.getPermissions();
    }

    @Override
    public void addPermission(String permissionname) {
        permissionMapper.addpermission(permissionname);
    }

    @Override
    public void deletepermission(int pid) {
        permissionMapper.deletepermission(pid);
        permissionMapper.deleteRole_permission(pid);
    }

    @Override
    public int getUidByusername(String username) {
        return userMapper.getUidByusername(username);
    }


}
