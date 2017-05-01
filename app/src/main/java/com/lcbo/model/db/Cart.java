package com.lcbo.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Cart {

    @Id(autoincrement = true)
    Long id;

    @Unique
    int productId;

    String productName;

    String productMl;

    String primaryCategory;

    String secondaryCategory;

    String productImg;

    int count;

    int cost;

    @Generated(hash = 733202094)
    public Cart(Long id, int productId, String productName, String productMl,
            String primaryCategory, String secondaryCategory, String productImg,
            int count, int cost) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productMl = productMl;
        this.primaryCategory = primaryCategory;
        this.secondaryCategory = secondaryCategory;
        this.productImg = productImg;
        this.count = count;
        this.cost = cost;
    }

    @Generated(hash = 1029823171)
    public Cart() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrimaryCategory() {
        return this.primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getSecondaryCategory() {
        return this.secondaryCategory;
    }

    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getProductImg() {
        return this.productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductMl() {
        return this.productMl;
    }

    public void setProductMl(String productMl) {
        this.productMl = productMl;
    }

}
