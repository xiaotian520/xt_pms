package com.xt.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.pms.dao.IOrdersDao;
import com.xt.pms.domain.Orders;
import com.xt.pms.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderSerice")
public class OrdersServiceImpl implements IOrdersService {


    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public void save(Orders orders) throws Exception {
        //return ordersDao;
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }

    @Override
    public void update(Orders orders) throws Exception {

    }

    @Override
    public void delete(String[] ids) {

    }
}
