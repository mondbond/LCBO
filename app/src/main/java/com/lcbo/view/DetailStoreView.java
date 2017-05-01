package com.lcbo.view;

import com.lcbo.model.pojo.Products.Result;
import com.lcbo.model.pojo.Products.StoreProducts;

import java.util.List;

public interface DetailStoreView {
    void setProductsData(StoreProducts storeProducts);
    void setProductsDataFromDb(List<Result> results);
    void showErrorMessage(String msg);
}
