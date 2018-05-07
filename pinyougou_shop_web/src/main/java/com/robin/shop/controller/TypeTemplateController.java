package com.robin.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.robin.pojo.TbTypeTemplate;
import com.robin.sellergoods.service.TypeTemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * pinyougou_parent
 * robin
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

    @Reference
    private TypeTemplateService typeTemplateService;

    @RequestMapping("/findOne")
    public TbTypeTemplate findOne(Long id){
        return typeTemplateService.findOne(id);
    }

    @RequestMapping("/findSpecList")
    public List<Map> findSpecList(Long id){
        return typeTemplateService.findSpecList(id);
    }



}
