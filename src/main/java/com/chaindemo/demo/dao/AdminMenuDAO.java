package com.chaindemo.demo.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaindemo.demo.model.AdminMenu;

public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
    AdminMenu findById(int id);
    List<AdminMenu> findAllByParentId(int parentId);
}
