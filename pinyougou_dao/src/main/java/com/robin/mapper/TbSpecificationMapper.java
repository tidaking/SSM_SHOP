package com.robin.mapper;

import com.robin.pojo.TbSpecification;
import com.robin.pojo.TbSpecificationExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbSpecificationMapper {
    List<Map> selectOptionList();

    int countByExample(TbSpecificationExample example);

    int deleteByExample(TbSpecificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbSpecification record);

    int insertSelective(TbSpecification record);

    List<TbSpecification> selectByExample(TbSpecificationExample example);

    TbSpecification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbSpecification record, @Param("example") TbSpecificationExample example);

    int updateByExample(@Param("record") TbSpecification record, @Param("example") TbSpecificationExample example);

    int updateByPrimaryKeySelective(TbSpecification record);

    int updateByPrimaryKey(TbSpecification record);
}