package com.chaindemo.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaindemo.demo.model.User;

public interface UserDAO extends JpaRepository<User,Integer> {
	User findByUserName(String userName);
	
	User findByUserNameAndPassword(String userName,String password);
}
