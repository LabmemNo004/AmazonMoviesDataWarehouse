package com.dwh.hive.service.serviceInterImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.hive.dao.ProductDAO;
import com.dwh.hive.pojo.Product;
import com.dwh.hive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl
        extends ServiceImpl<ProductDAO, Product> implements ProductService {
    @Autowired
    ProductDAO productDAO;
}
