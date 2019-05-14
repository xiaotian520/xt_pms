package com.xt.pms.service;

import com.xt.pms.domain.Permission;
import com.xt.pms.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll(int page, int size) throws  Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    Role findRoleById(String roleId) throws Exception;

    void update(Role role) throws Exception;

    void delete(String[] ids) throws Exception;

    List<Permission> findOtherPermission(String roleid) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
