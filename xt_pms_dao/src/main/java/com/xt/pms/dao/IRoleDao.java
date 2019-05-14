package com.xt.pms.dao;

import com.xt.pms.domain.Permission;
import com.xt.pms.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户id出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result( property = "roleName", column = "roleName"),
            @Result( property = "roleDesc", column = "roleDesc"),
            @Result( property = "permissions", column = "id", javaType = List.class,many = @Many(select = "com.xt.pms.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleid = #{roleid})")
    List<Permission> findOtherPermissions(String roleid) throws Exception;

    @Insert("insert into role_permission(permissionId,roleId) values(#{pId},#{roleId})")
    void addRoleToUser(@Param("roleId") String roleId, @Param("pId") String pId) throws Exception;


    @Select("select * from role where id = #{id}")
    @Results({
            @Result( id = true, property = "id", column = "id"),
            @Result( property = "roleName", column = "roleName"),
            @Result( property = "roleDesc", column = "roleDesc"),
            @Result( property = "permissions", column = "id", javaType = List.class,many = @Many(select = "com.xt.pms.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public Role findRoleById(String id) throws Exception;

    @Select("select * from role where id = #{id}")
    public Role findById(String id) throws Exception;
}
