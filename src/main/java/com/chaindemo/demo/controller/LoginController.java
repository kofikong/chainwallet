package com.chaindemo.demo.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.chaindemo.demo.model.User;
import com.chaindemo.demo.result.Result;
import com.chaindemo.demo.result.ResultFactory;
import com.chaindemo.demo.service.UserService;
import com.chaindemo.demo.utils.JwtHelper;

import net.sf.json.JSONObject;

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
        
        
        Subject subject = SecurityUtils.getSubject();
//      subject.getSession().setTimeout(10000);
      UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
      usernamePasswordToken.setRememberMe(true);
      try {
          User user = userService.getByName(username);
          if (!user.isEnabled()) {
              String message = "该用户已被禁用";
              return ResultFactory.buildFailResult(message);
          }
          subject.login(usernamePasswordToken);
          // 生成随机 token 并存储在 session 中
          return ResultFactory.buildSuccessResult(usernamePasswordToken);

      } catch (AuthenticationException e) {
          String message = "账号或密码错误";
          return ResultFactory.buildFailResult(message);
      }
	}
	
	@PostMapping("/api/register")
    public Result register(@RequestBody User user) {
        String username = user.getUserName();
        String password = user.getPassword();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        
        username = HtmlUtils.htmlEscape(username);
        user.setUserName(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(true);

        if (username.equals("") || password.equals("")) {
            String message = "用户名或密码为空，注册失败";
            return ResultFactory.buildFailResult(message);
        }

        boolean exist = userService.isExist(username);

        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.addOrUpdate(user);

        return ResultFactory.buildSuccessResult(user);
    }
	
	@GetMapping("/api/logout")
	public Result logout(){
		Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
	}
	
	@GetMapping(value = "api/authentication")
    public String authentication() {
        return "身份认证成功";
    }
}
