package com.robin.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.robin.pojo.TbItemCat;
import com.robin.sellergoods.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * pinyougou_parent
 * robin
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {


    @Reference
    private ItemCatService itemCatService;

    @RequestMapping("/findByParentId")
    public List<TbItemCat> findByParentId(Long parentId){
        System.out.println(parentId);
        return itemCatService.findByParentId(parentId);
    }


    @RequestMapping("/findOne")
    public TbItemCat findOne(Long id){
        return itemCatService.findOne(id);
    }


}
