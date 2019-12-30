package com.chaindemo.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.chaindemo.demo.model.User;
import com.chaindemo.demo.server.Result;

@RestController()
public class LoginController {
	@RequestMapping(value="/api/login1",method= RequestMethod.POST)
	public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUserName();
     // 对 html 标签进行转义，防止 XSS 攻击
        username = HtmlUtils.htmlEscape(username);
        
        if (!StringUtils.equals("admin", username) || !StringUtils.equals("123456", requestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }
	}

	@RequestMapping(value="/api/login",method= RequestMethod.POST)
	public Result login(@RequestParam(value = "userName", defaultValue = "") String userName,@RequestParam(value="password",defaultValue="") String password) {
     // 对 html 标签进行转义，防止 XSS 攻击
		userName = HtmlUtils.htmlEscape(userName);
        
        if (!StringUtils.equals("admin", userName) || !StringUtils.equals("123456", password)) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }
	}
}
