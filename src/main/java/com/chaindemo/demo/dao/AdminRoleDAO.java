package com.chaindemo.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaindemo.demo.model.AdminRole;

public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);
}