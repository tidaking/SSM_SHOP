package com.robin.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.robin.pojo.TbGoods;
import com.robin.pojogroup.Goods;
import com.robin.sellergoods.service.GoodsService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pinyougou_parent
 * robin
 */

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;


    @RequestMapping("/add")
    public Result add(@RequestBody Goods goods){
        try {
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            goods.getGoods().setSellerId(sellerId);
            goodsService.add(goods);
            return new Result(true,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增失败");
        }
    }
}
