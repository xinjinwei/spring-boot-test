package com.xboot.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xboot.mapper.db1.User1Mapper;
import com.xboot.mapper.db2.User2Mapper;
import com.xboot.mapper.model.User;



@Controller
@RequestMapping("/mybatis_page")
public class UserController_Page {

    @Autowired
    private User1Mapper user1Mapper;

	@Autowired
	private User2Mapper user2Mapper;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		List<User> users=user1Mapper.getAll();
        users.addAll(user2Mapper.getAll());
		return users;
	}
	@RequestMapping("/getUsers_page")
	public List<User> getUsers_page(HttpServletRequest request, int pageon, int pageSize) {
		// 设置页码、条数
		
		PageHelper.startPage(pageon, pageSize);
		// 查询
		PageInfo<User> pageInfo = new PageInfo<>(user1Mapper.getAll());
		List<User> users=pageInfo.getList();
		return users;
	}
	
    @RequestMapping("/list")
    public String list(ModelMap map,HttpServletRequest request, int pageon, int pageSize) {
		// 设置页码、条数
		
		PageHelper.startPage(pageon, pageSize);
		// 查询
		PageInfo<User> pageInfo = new PageInfo<>(user1Mapper.getAll());
		List<User> users=pageInfo.getList();        
		map.addAttribute("users", users);
        return "test/mybatis/list";
    }
	
	
    @RequestMapping("/getUser")
    public User getUser(Long id) {
    	User user=user2Mapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(User user) {
        user2Mapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(User user) {
        user2Mapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
    }
    
    
}