package com.xt.pms.controller;

import com.github.pagehelper.PageInfo;
import com.xt.pms.domain.Permission;
import com.xt.pms.domain.Role;
import com.xt.pms.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    //给角色添加权限
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception{
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    //*查询角色以及角色可以添加的权限*/
    @RequestMapping("findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleid) throws Exception{
        ModelAndView mv = new ModelAndView();
        //1.根据角色id查询角色
        Role role = roleService.findById(roleid);
        //2.根据角色id查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermission(roleid);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }


    /*查询所有商品*/
    @RequestMapping("findAll.do")
    @Secured("ROLE_ADMIN")//不能省略前缀//不用导Jar包
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> ps =  roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-page-list");
        return mv;
    }

    /*增加商品*/
    @RequestMapping("save.do")
    public String SaveRole(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /*到更新产品页面*/
    @RequestMapping("toUpdate.do")
    public ModelAndView ToUpdate(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-edit");
        return mv;
    }

    /*更新产品信息*/
    @RequestMapping("update.do")
    public String UpdateRole(Role role) throws Exception{
        roleService.update(role);
        return "redirect:findAll.do";
    }

    /*删除产品*/
    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public String DeleteRole(String[] ids){
       try {
           roleService.delete(ids);
           return "success";
       }catch (Exception e){
            return "fail";
       }

    }

    /*查询指定Id的角色*/
    @RequestMapping("findRoleById.do")
    public ModelAndView findRoleById(String id) throws Exception{
        System.out.println(id+"-------------------");
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findRoleById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

}
