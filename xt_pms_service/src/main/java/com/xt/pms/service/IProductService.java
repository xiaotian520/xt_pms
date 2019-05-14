package com.xt.pms.service;


import com.xt.pms.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll(int page, int size) throws  Exception;

    void save(Product product) throws Exception;

    Product findById(String id) throws Exception;

    void update(Product product) throws Exception;

    void delete(String[] ids);
}
