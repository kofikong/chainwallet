package com.chaindemo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chaindemo.demo.dao.AdminUserRoleDAO;
import com.chaindemo.demo.model.AdminRole;
import com.chaindemo.demo.model.AdminUserRole;

@Service
public class AdminUserRoleService {
	@Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }

//    @Modifying
    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDAO.deleteAllByUid(uid);
        for (AdminRole role : roles) {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(role.getId());
            adminUserRoleDAO.save(ur);
        }
    }
}
