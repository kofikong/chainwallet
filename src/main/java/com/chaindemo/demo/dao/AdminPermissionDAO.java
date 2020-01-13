package com.chaindemo.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaindemo.demo.model.AdminPermission;

public interface AdminPermissionDAO extends JpaRepository<AdminPermission, Integer> {
    AdminPermission findById(int id);
}