package top.kylewang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.kylewang.VO.DataGrid;
import top.kylewang.VO.Userinfo;
import top.kylewang.pojo.Permission;
import top.kylewang.pojo.Role;
import top.kylewang.pojo.User;
import top.kylewang.pojo.User_role;
import top.kylewang.service.SystemService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SystemController {
	@Autowired
	SystemService systemService;
	
	@RequestMapping("/useradmin")
	String useradmin(){
		return "system/useradmin";
	}
	
	@RequestMapping("/roleadmin")
	String roleadmin(){
		return "system/roleadmin";
	}
	
	@RequestMapping("/permissionadmin")
	String permissionadmin(){
		return "system/permissionadmin";
	}
	
	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	@ResponseBody
	DataGrid<Userinfo> userlist(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount){
		int total=systemService.getallusers().size();
		List<User> userlist=systemService.getpageusers(current,rowCount);
		List<Userinfo> users=new ArrayList<Userinfo>();
		for(User user:userlist){
			Userinfo u=new Userinfo();
			u.setId(user.getUid());
			u.setAge(user.getAge());
			u.setPassword(user.getPassword());
			u.setTel(user.getTel());
			u.setUsername(user.getUsername());
			String rolename="";
			List<User_role> ur=user.getUser_roles();
			if(ur!=null){
				for(User_role userole:ur){
					rolename=rolename+","+userole.getRole().getRolename();
				}
				if(rolename.length()>0)
				rolename=rolename.substring(1,rolename.length());
				
				u.setRolelist(rolename);
			}
			users.add(u);
		}
		DataGrid<Userinfo> grid=new DataGrid<Userinfo>();
		grid.setCurrent(current);
		grid.setRows(users);
		grid.setRowCount(rowCount);
		grid.setTotal(total);
		return grid;
	}
	
	@RequestMapping(value="/user/{uid}",method=RequestMethod.GET)
	@ResponseBody
	User getuserinfo(@PathVariable("uid") int userid){
		return systemService.getUserByid(userid);
	}
	
	@RequestMapping(value="/rolelist",method=RequestMethod.GET)
	@ResponseBody
	List<Role> getroles(){
		return systemService.getRoles();
	}
	
	@RequestMapping(value="/roles",method=RequestMethod.GET)
	@ResponseBody
	DataGrid<Role> getallroles(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount){
		List<Role> roles=systemService.getRoleinfo();
		List<Role> rows=systemService.getpageRoleinfo(current, rowCount);
		DataGrid<Role> grid=new DataGrid<Role>();
		grid.setCurrent(current);
		grid.setRowCount(rowCount);
		grid.setTotal(roles.size());
		grid.setRows(rows);
		return grid;
	}
	
	@RequestMapping(value="/deleteuser/{uid}",method=RequestMethod.GET)
	String deleteuser(@PathVariable("uid")int uid){
		systemService.deleteuser(uid);
		return "system/useradmin";
	}
	
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	String adduser(@ModelAttribute("user")User user,@RequestParam(value="rolename[]",required = false)String[] rolename){
		if(rolename==null)
			systemService.adduser(user);
		else
			systemService.adduser(user, rolename);
		return "forward:/useradmin";
	}
	
	@RequestMapping(value="/updateuser/{uid}",method=RequestMethod.POST)
	String updateuser(@PathVariable("uid")int uid,@ModelAttribute("user")User user,@RequestParam(value="rolename[]",required = false)String[] rolename){
		systemService.updateuser(uid, user, rolename);
		return "system/useradmin";
	}
	
	
	@RequestMapping(value="permissionlist",method=RequestMethod.GET)
	@ResponseBody
	List<Permission> getPermisions(){
		return systemService.getPermisions();
	}
	
	@RequestMapping(value="addrole",method=RequestMethod.POST)
	String addrole(@RequestParam("rolename") String rolename,@RequestParam(value="permissionname[]")String[] permissionname){
		Role r=new Role();
		r.setRolename(rolename);
		systemService.addrole(r, permissionname);
		return "forward:/roleadmin";
	}
	
	@RequestMapping(value="/deleterole/{rid}",method=RequestMethod.GET)
	String deleterole(@PathVariable("rid")int rid){
		systemService.deleterole(rid);
		return "system/roleadmin";
	}
	
	@RequestMapping(value="roleinfo/{rid}",method=RequestMethod.GET)
	@ResponseBody
	Role getRolebyrid(@PathVariable("rid")int rid){
		return systemService.getRolebyid(rid);
	}
	
	@RequestMapping(value="updaterole/{rid}",method=RequestMethod.POST)
	String updaterole(@PathVariable("rid")int rid,@RequestParam(value="permissionname[]")String[] permissionnames){
		systemService.deleterolepermission(rid);
		systemService.updaterole(rid, permissionnames);
		return "system/roleadmin";
	}
	
	
	@RequestMapping(value="permissions",method=RequestMethod.GET)
	@ResponseBody
	DataGrid<Permission> getpermissions(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount){
		List<Permission> p=systemService.getPermisions();
		List<Permission> list=systemService.getPagePermisions(current, rowCount);
		DataGrid<Permission> grid=new DataGrid<Permission>();
		grid.setCurrent(current);
		grid.setRowCount(rowCount);
		grid.setTotal(p.size());
		grid.setRows(list);
		return grid;
	}
	
	@RequestMapping(value="addpermission",method=RequestMethod.POST)
	String addpermission(@RequestParam("permissionname") String permissionname){
		systemService.addPermission(permissionname);
		return "system/permissionadmin";
	}
	
	
	@RequestMapping(value="deletepermission/{pid}",method=RequestMethod.GET)
	String deletepermission(@PathVariable("pid") int pid){
		systemService.deletepermission(pid);
		return "system/permissionadmin";
	}
}
