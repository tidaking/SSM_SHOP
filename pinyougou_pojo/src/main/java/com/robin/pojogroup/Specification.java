package com.robin.pojogroup;

import com.robin.pojo.TbSpecification;
import com.robin.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @author robin
 * @version 1.0
 * @description 描述
 * @title 标题
 */
public class Specification implements Serializable {
    private TbSpecification specification;//规格
    private List<TbSpecificationOption> specificationOptionList;//规格选项列表

    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
