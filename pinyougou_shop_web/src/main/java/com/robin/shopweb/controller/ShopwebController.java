package com.robin.shopweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.robin.sellergoods.service.SellerGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("shopweb")
public class ShopwebController {

    @Reference
    private SellerGoodsService sellerGoodsService;

    @ResponseBody
    @RequestMapping("test/{name}")
    public String test(@PathVariable String name)
    {
        System.out.println("controller:"+name);

        String ret = sellerGoodsService.test(name);

        System.out.println("ret:"+ret);

        return ret;
    }

}
