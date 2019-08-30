package com.erhuo.erhuoshop.bean;

public class NewCatrgory {
    private int imgid;
    private String name;
    private String price;

    public NewCatrgory(int imgid, String name, String price) {
        this.imgid = imgid;
        this.name = name;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
