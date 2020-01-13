package com.chaindemo.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaindemo.demo.model.AdminUserRole;

public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    List<AdminUserRole> findAllByUid(int uid);
    void deleteAllByUid(int uid);
}
