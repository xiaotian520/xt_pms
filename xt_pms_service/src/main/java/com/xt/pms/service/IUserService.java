package com.xt.pms.service;

import com.xt.pms.domain.Role;
import com.xt.pms.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll(int page, int size) throws  Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    void update(UserInfo userInfo) throws Exception;

    void delete(String[] ids);

    List<Role> findOtherRole(String userid) throws Exception;

    void addRoleToUser(String userId, String[] roleIds);
}
