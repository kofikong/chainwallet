package com.chaindemo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaindemo.demo.dao.UserDAO;
import com.chaindemo.demo.model.User;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	public boolean isExist(String userName) {
		User user = getByName(userName);
		return user != null;
	}

	public User getByName(String userName) {
		return userDAO.findByUserName(userName);
	}
	
	public User get(String userName,String password) {
		return userDAO.findByUserNameAndPassword(userName, password);
	}
	
	public void addOrUpdate(User user) {
        userDAO.save(user);
    }
}
