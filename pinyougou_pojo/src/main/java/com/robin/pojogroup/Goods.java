package com.robin.pojogroup;

import com.robin.pojo.TbGoods;
import com.robin.pojo.TbGoodsDesc;
import com.robin.pojo.TbItem;

import java.io.Serializable;
import java.util.List;

/**
 * pinyougou_parent
 * robin
 */
public class Goods implements Serializable {
    private TbGoods goods;//商品SPU基本信息
    private TbGoodsDesc goodsDesc;//商品描述
    private List<TbItem> itemList;//商品SKU列表

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<TbItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TbItem> itemList) {
        this.itemList = itemList;
    }
}
