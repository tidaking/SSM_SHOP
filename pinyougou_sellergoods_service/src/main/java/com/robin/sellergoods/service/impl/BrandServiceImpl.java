package com.robin.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.robin.mapper.TbBrandMapper;
import com.robin.pojo.TbBrand;
import com.robin.pojo.TbBrandExample;
import com.robin.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        //只对后面紧跟着的第一条查询进行分页
        PageHelper.startPage(pageNum,pageSize);
        PageResult pageResult = new PageResult();

        Page<TbBrand> page = (Page)tbBrandMapper.selectByExample(null);

        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @Override
    public void add(TbBrand brand) {
        tbBrandMapper.insert(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand brand) {
        tbBrandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Long[] ids) {
        TbBrandExample exmaple = new TbBrandExample();
        TbBrandExample.Criteria criteria = exmaple.createCriteria();
        List<Long> list = new ArrayList<>();
        for(Long id:ids){
            list.add(id);
        }
        criteria.andIdIn(list);
        tbBrandMapper.deleteByExample(exmaple);
    }

    @Override
    public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        PageResult pageResult = new PageResult();
        TbBrandExample example = null;

        if(brand != null){
            example = new TbBrandExample();
            TbBrandExample.Criteria criteria = example.createCriteria();
            if(brand.getName() != null && brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }

            if(brand.getFirstChar() != null && brand.getFirstChar().length()>0){
                criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(example);
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(page.getResult());

        return pageResult;
    }
}
