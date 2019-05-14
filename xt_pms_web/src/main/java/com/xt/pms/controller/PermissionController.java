package com.xt.pms.controller;

import com.github.pagehelper.PageInfo;
import com.xt.pms.domain.Permission;
import com.xt.pms.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /*查询所有*/
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> ps =  permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-page-list");
        return mv;
    }

    /*增加*/
    @RequestMapping("save.do")
    public String Savepermission(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /*到更新页面*/
    @RequestMapping("toUpdate.do")
    public ModelAndView ToUpdate(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-edit");
        return mv;
    }

    /*更新信息*/
    @RequestMapping("update.do")
    public String Updatepermission(Permission permission) throws Exception{
        permissionService.update(permission);
        return "redirect:findAll.do";
    }

    /*删除*/
    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public String Deletepermission(String[] ids){
       try {
           permissionService.delete(ids);
           return "success";
       }catch (Exception e){
            return "fail";
       }

    }
}
