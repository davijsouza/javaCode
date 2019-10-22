package com.souche.salt_common_01.controller;


import com.souche.salt_common_01.entity.CurrentScript;
import com.souche.salt_common_01.service.impl.CacheDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class EhcacheController {
	@Autowired
	private CacheDaoImpl cacheDao;
	@RequestMapping("/selectUser")
	public String selectUser(@PathParam("id") String id) {
		CurrentScript user = cacheDao.select(id);
		return user.toString();
	}
	@RequestMapping("/saveUser")
	public String saveUser(@PathParam("id")String id, @PathParam("username") String username, @PathParam("password") String password) {
		CurrentScript user = new CurrentScript( );
		user.setId(id);
		cacheDao.save(user);
		return user.toString();
	}
	@RequestMapping("/updateUser")
	public String updateUser(@PathParam("id") String id, @PathParam("username") String username, @PathParam("password") String password){
		CurrentScript user = cacheDao.select(id);
		user.setId(id);
		 //

		cacheDao.update(user);
		return user.toString();
	}
	@RequestMapping("/deleteUser")
	public String updateUser(@PathParam("id") String id){
		CurrentScript user = cacheDao.select(id);
		cacheDao.delete(user);
		return "<a href='/user/selectUser?id="+id+"'>查找人员</a>";
	}
	@RequestMapping("/deleteAll")
	public String deleteAll() {
		cacheDao.deleteAll();
		return "清除所有缓存";
	}
	@RequestMapping("/selectFromOtherCache")
	public String selectFromOtherCache() {
		return cacheDao.selectFromOtherCache();
	}

	/*@RequestMapping("/selectAll")
	public String selectAll() {
		return cacheDao.selectAll();
	}*/
}
