package com.xt.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.pms.dao.IProductDao;
import com.xt.pms.domain.Product;
import com.xt.pms.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    /*查询产品*/
    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    /*增加产品*/
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    /*通过Id查找产品*/
    @Override
    public Product findById(String id) throws Exception {
        return productDao.findById(id);
    }
    /*更新产品*/
    @Override
    public void update(Product product) throws Exception {
        productDao.update(product);
    }
    /*删除产品*/
    @Override
    public void delete(String[] ids) {
        productDao.delete(ids);
    }
}
