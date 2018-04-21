package com.robin.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.robin.sellergoods.service.SellerGoodsService;

@Service
public class SellerGoodsServiceImpl implements SellerGoodsService {


    @Override
    public String test(String name) {
        System.out.println("dubbo test service:"+name);
        return "test:"+name;
    }
}
