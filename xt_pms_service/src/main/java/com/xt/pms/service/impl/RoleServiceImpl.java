package com.xt.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.pms.dao.IRoleDao;
import com.xt.pms.domain.Permission;
import com.xt.pms.domain.Role;
import com.xt.pms.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }
@Override
    public Role findRoleById(String roleId) throws Exception {
        return roleDao.findRoleById(roleId);
    }

    @Override
    public void update(Role role) throws Exception {

    }

    @Override
    public void delete(String[] ids) throws Exception {

    }

    @Override
    public List<Permission> findOtherPermission(String roleid) throws Exception {
        return roleDao.findOtherPermissions(roleid);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String pId:permissionIds) {
            roleDao.addRoleToUser(roleId,pId);
        }
    }


}
