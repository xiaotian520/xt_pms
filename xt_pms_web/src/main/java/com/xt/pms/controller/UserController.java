package com.xt.pms.controller;

import com.github.pagehelper.PageInfo;
import com.xt.pms.domain.Role;
import com.xt.pms.domain.UserInfo;
import com.xt.pms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }


    /*查询用户以及用户可以添加的角色*/
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userid) throws Exception{
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userid);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRole(userid);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /*查询所有用户*/
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception {
        System.out.println(page+"--"+size);
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList =  userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-page-list");
        return mv;
    }

    /*增加用户*/
    @RequestMapping("save.do")
    /*@PreAuthorize("authentication.principal.username == 'tom'")*/
    public String SaveUser(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /*到更新用户页面*/
    @RequestMapping("toUpdate.do")
    public ModelAndView ToUpdate(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-edit");
        return mv;
    }

    /*更新用户信息*/
    @RequestMapping("update.do")
    public String UpdateUser(UserInfo userInfo) throws Exception{
        userService.update(userInfo);
        return "redirect:findAll.do";
    }

    /*删除用户*/
    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public String DeleteUser(String[] ids){
        try {
            userService.delete(ids);
            return "success";
        }catch (Exception e){
            return "fail";
        }
    }


    /*查询指定Id的用户*/
    @RequestMapping("findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }


}
