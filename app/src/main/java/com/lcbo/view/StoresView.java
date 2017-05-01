package com.lcbo.view;

import com.lcbo.model.pojo.Stores.Result;
import com.lcbo.model.pojo.Stores.Stores;

import java.util.ArrayList;

public interface StoresView {
    void setStoresData(Stores stores);
    void setStoresDataFromBd(ArrayList<Result> results);
    void showErrorMessage(String msg);
}
