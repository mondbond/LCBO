package com.lcbo.view;

import com.lcbo.model.pojo.Products.Result;
import com.lcbo.model.pojo.Products.StoreProducts;

import java.util.List;

public interface ProductsView {
    void setProductsData(StoreProducts products);
    void setProductsDataFromBd(List<Result> results);
    void showErrorMessage(String msg);
}
