package com.robin.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.robin.pojo.TbSeller;
import com.robin.sellergoods.service.SellerService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * pinyougou_parent
 * robin
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    SellerService sellerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/add")
    public Result add(@RequestBody TbSeller seller){
        try {
            seller.setPassword(passwordEncoder.encode(seller.getPassword()));
            sellerService.add(seller);
            return new Result(true,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增失败");
        }
    }


    @RequestMapping("/name")
    public Map<String,String> name(){
        System.out.println("收到请求...");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,String> map = new HashMap<String,String>();
        map.put("loginName",name);
        return map;
    }
}
