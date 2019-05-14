package com.xt.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.pms.dao.IPermissionDao;
import com.xt.pms.domain.Permission;
import com.xt.pms.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return null;
    }

    @Override
    public void update(Permission permission) throws Exception {

    }

    @Override
    public void delete(String[] ids) {

    }
}
