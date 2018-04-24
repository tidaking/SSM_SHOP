package com.robin.sellergoods.service;

import com.robin.pojo.TbBrand;
import entity.PageResult;

import java.util.List;

public interface BrandService {

    public List<TbBrand> findAll();

    public PageResult findPage(Integer pageNum,Integer pageSize);


    /**
     * 插入数据
     * @param brand
     */
    public void add(TbBrand brand);

    public TbBrand findOne(Long id);

    /**
     * brand是更新后的数据 一定要带有Id
     * @param brand
     */
    public void update(TbBrand brand);

    public void delete(Long[] ids);


    public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize);
}
