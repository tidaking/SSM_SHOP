package com.robin.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.robin.pojo.TbSeller;
import com.robin.sellergoods.service.SellerService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pinyougou_parent
 * robin
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    SellerService sellerService;

    @RequestMapping("/add")
    public Result add(@RequestBody TbSeller seller){
        try {
            sellerService.add(seller);
            return new Result(true,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增失败");
        }

    }
}
