package com.chaindemo.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chaindemo.demo.model.AdminRoleMenu;

import java.util.List;

public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu,Integer> {
    List<AdminRoleMenu> findAllByRid(int rid);
    void deleteAllByRid(int rid);
}