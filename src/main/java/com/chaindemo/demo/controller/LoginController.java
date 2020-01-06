package com.chaindemo.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.chaindemo.demo.model.User;
import com.chaindemo.demo.service.UserService;
import com.chaindemo.demo.utils.JwtHelper;

import net.sf.json.JSONObject;

@RestController()
public class LoginController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/api/login",method= RequestMethod.POST)
	public ResponseEntity<JSONObject> login(@RequestBody User requestUser,HttpSession session) {
        String username = requestUser != null ? requestUser.getUserName() : "";
        String password = requestUser != null ? requestUser.getPassword() : "";
     // 对 html 标签进行转义，防止 XSS 攻击
        username = HtmlUtils.htmlEscape(username);
        password = HtmlUtils.htmlEscape(password);
        
        User user = userService.get(username, password);
        
        String jsonWebKey = JwtHelper.generateJWT(String.valueOf(user.getId()), user.getUserName(),"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        System.out.println(jsonWebKey);
        
        HttpHeaders headers = new HttpHeaders();
        //根据自己的需要动态添加你想要的content type
        headers.add(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, jsonWebKey);
        
        JSONObject obj = JSONObject.fromObject(user);
        obj.remove("password");
        return new ResponseEntity<JSONObject>(obj,headers,HttpStatus.OK);

//        Claims claims =  JwtHelper.parseJWT(jsonWebKey);
//         System.out.println(claims);
//         return claims;
//        System.out.println(JwtHelper.validateLogin(jsonWebKey));
        
//        if(user == null){
//            String message = "账号密码错误";
//            System.out.println("test");
//            return new Result(400);
//        } else {
//        	session.setAttribute("user", user);
//            return new Result(200);
//        }
	}
}
