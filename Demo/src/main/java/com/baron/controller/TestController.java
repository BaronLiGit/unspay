package com.baron.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baron.entity.User;
import com.baron.service.UserService;

@Controller
public class TestController {
	@Resource  
    private UserService userService;  
	
	@Value("${jdbc_username}")
	private String name;
	
	@RequestMapping("/showUserInfo")    
	public ModelAndView hello(){
		System.out.println(name);
		ModelAndView mav = new ModelAndView("index");   
		User user = userService.selectUserById(1);  
        mav.addObject("user", user);   
		return mav;
	}
}
