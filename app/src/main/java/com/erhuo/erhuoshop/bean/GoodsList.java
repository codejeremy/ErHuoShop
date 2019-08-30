package com.erhuo.erhuoshop.bean;

public class GoodsList {
    private int imgurlid;
    private String goods_descripe;
    private String goods_price;

    public GoodsList(int imgurlid, String goods_descripe, String goods_price) {
        this.imgurlid = imgurlid;
        this.goods_descripe = goods_descripe;
        this.goods_price = goods_price;
    }

    public int getImgurlid() {
        return imgurlid;
    }

    public void setImgurlid(int imgurlid) {
        this.imgurlid = imgurlid;
    }

    public String getGoods_descripe() {
        return goods_descripe;
    }

    public void setGoods_descripe(String goods_descripe) {
        this.goods_descripe = goods_descripe;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }
}
