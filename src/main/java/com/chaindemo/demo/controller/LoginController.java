package com.chaindemo.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.chaindemo.demo.model.User;
import com.chaindemo.demo.server.Result;
import com.chaindemo.demo.service.UserService;

@RestController()
public class LoginController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/api/login",method= RequestMethod.POST)
	public Result login(@RequestBody User requestUser,HttpSession session) {
        String username = requestUser != null ? requestUser.getUserName() : "";
        String password = requestUser != null ? requestUser.getPassword() : "";
     // 对 html 标签进行转义，防止 XSS 攻击
        username = HtmlUtils.htmlEscape(username);
        password = HtmlUtils.htmlEscape(password);
        
        User user = userService.get(username, password); 
        if(user == null){
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
        	session.setAttribute("user", user);
            return new Result(200);
        }
	}
}
