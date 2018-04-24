package com.robin.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.robin.pojo.TbBrand;
import com.robin.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import entity.TestPojo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;


    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        List<TbBrand> tbBrands = brandService.findAll();
        return tbBrands;
    }


    @RequestMapping("/findPage")
    //page:查询的页码
    //rows:每页显示条目数
    public PageResult findPage(Integer pageNum,Integer pageSize){
        return brandService.findPage(pageNum, pageSize);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody  TbBrand brand){
        try {
            brandService.add(brand);
            return new Result(true,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增失败");
        }
    }

    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }


    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand)
    {
        try {
            brandService.update(brand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,"修改失败");
        }
    }

    @RequestMapping("/dele")
    public Result dele(@RequestBody Long[] ids){
        for(Long id:ids){
            System.out.println(id);
        }
        brandService.delete(ids);
        return new Result(true,"删除成功");
    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand,Integer pageNum,Integer pageSize){

        return brandService.findPage(brand,pageNum,pageSize);
    }


    @RequestMapping("/test")
    public String test(TestPojo pojo,String normalMessage){
        System.out.println(pojo);
        System.out.println(normalMessage);
        return "test2.html";
    }


}
