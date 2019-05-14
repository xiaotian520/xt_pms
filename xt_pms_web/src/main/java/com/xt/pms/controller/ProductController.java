package com.xt.pms.controller;

import com.github.pagehelper.PageInfo;
import com.xt.pms.domain.Product;
import com.xt.pms.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /*查询所有商品*/
    @RequestMapping("findAll.do")
    @RolesAllowed("ADMIN")//可以省略前缀//需要导Jar包
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps =  productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-page-list");
        return mv;
    }

    /*增加商品*/
    @RequestMapping("save.do")
    public String SaveProduct(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    /*到更新产品页面*/
    @RequestMapping("toUpdate.do")
    public ModelAndView ToUpdate(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-edit");
        return mv;
    }

    /*更新产品信息*/
    @RequestMapping("update.do")
    public String UpdateProduct(Product product) throws Exception{
        productService.update(product);
        return "redirect:findAll.do";
    }

    /*删除产品*/
    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public String DeleteProduct(String[] ids){
       try {
           productService.delete(ids);
           return "success";
       }catch (Exception e){
            return "fail";
       }

    }
}
