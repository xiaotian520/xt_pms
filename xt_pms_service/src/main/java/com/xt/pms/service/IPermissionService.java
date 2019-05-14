package com.xt.pms.service;

import com.xt.pms.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll(int page, int size) throws  Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String id) throws Exception;

    void update(Permission permission) throws Exception;

    void delete(String[] ids);
}
