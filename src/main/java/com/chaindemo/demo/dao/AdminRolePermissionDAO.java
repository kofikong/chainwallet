package com.chaindemo.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chaindemo.demo.model.AdminRolePermission;

import java.util.List;


public interface AdminRolePermissionDAO extends JpaRepository<AdminRolePermission, Integer> {
    List<AdminRolePermission> findAllByRid(int rid);
    void deleteAllByRid(int rid);
}