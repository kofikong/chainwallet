package com.chaindemo.demo.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class LoginController {
	@RequestMapping(value="/api/user/login",method= RequestMethod.POST)
	public JSONObject login(@RequestParam(value="userName", defaultValue="") String userName, @RequestParam(value="password",defaultValue="") String password) {
		
		return null;
	}

}
