package com.xt.pms.controller;

import com.github.pagehelper.PageInfo;
import com.xt.pms.domain.Orders;
import com.xt.pms.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    /*查询所有订单*/
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList =  ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("order-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }



    /*增加商品*/
    @RequestMapping("save.do")
    public String SaveOrders(Orders orders) throws Exception {
        ordersService.save(orders);
        return "redirect:findAll.do";
    }

    /*到更新产品页面*/
    @RequestMapping("toUpdate.do")
    public ModelAndView ToUpdate(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("order-edit");
        return mv;
    }

    /*更新产品信息*/
    @RequestMapping("update.do")
    public String UpdateOrders(Orders orders) throws Exception{
        ordersService.update(orders);
        return "redirect:findAll.do";
    }

    /*删除产品*/
    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public String DeleteProduct(String[] ids){
        try {
            ordersService.delete(ids);
            return "success";
        }catch (Exception e){
            return "fail";
        }

    }
}
