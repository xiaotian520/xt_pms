package com.xt.pms.service;

import com.xt.pms.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page, int size) throws Exception;

    void save(Orders orders) throws Exception;

    Orders findById(String id) throws Exception;

    void update(Orders orders) throws Exception;

    void delete(String[] ids);
}
