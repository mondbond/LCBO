package com.lcbo.presenters;

import android.content.Context;

import com.lcbo.R;
import com.lcbo.common.BasePresenter;
import com.lcbo.model.DbModel;
import com.lcbo.model.NetworkData;
import com.lcbo.model.pojo.Products.StoreProducts;
import com.lcbo.util.Util;
import com.lcbo.view.ProductsView;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Retrofit;
import rx.Subscriber;

public class ProductsPresenter implements BasePresenter<ProductsView> {

    private ProductsView mView;
    private NetworkData mNetwork;
    private Retrofit mRetrofit;
    private DbModel mDbModel;
    private Context mContext;


    @Inject
    public ProductsPresenter(NetworkData networkData, @Named("lcboAPI") Retrofit retrofit, DbModel
            dbModel, Context context) {
        mNetwork = networkData;
        mRetrofit = retrofit;
        mDbModel = dbModel;
        mContext = context;
    }

    @Override
    public void init(ProductsView view) {
        mView = view;
    }

    public void getProductsByNameAndFilters(String productName, String filter, int page) {

        if (Util.isNetworkAvailable(mContext)) {
            mNetwork.getProductsDataByNameAndFilters(mRetrofit, productName, filter, page).subscribe(new Subscriber<StoreProducts>() {
                @Override
                public void onCompleted() {
                }
                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(StoreProducts storeProducts) {
                    mView.setProductsData(storeProducts);
                    mDbModel.addProductsToBd(storeProducts).subscribe();
                }
            });
        }else {
            mDbModel.getAllProductByNameAndFilter(productName, filter).subscribe(results -> {
                if(results.isEmpty()){
                    mView.showErrorMessage(mContext.getResources().getString(R.string.turn_on_network_connection_error_msg));
                }
                mView.setProductsDataFromBd(results);
            });
        }
    }

    public void getProductsByCategory(String categoryName, int page){
        if (Util.isNetworkAvailable(mContext)) {
            mNetwork.getProductsDataByNameAndFilters(mRetrofit, categoryName, "", page).subscribe(new Subscriber<StoreProducts>() {
                @Override
                public void onCompleted() {}
                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(StoreProducts storeProducts) {
                    mView.setProductsData(storeProducts);
                    mDbModel.addProductsToBd(storeProducts).subscribe();
                }
            });
        }else {
            mDbModel.getAllProductsByCategory(categoryName).subscribe(results -> {
                if(results.isEmpty()){
                    mView.showErrorMessage(mContext.getResources().getString(R.string.turn_on_network_connection_error_msg));
                }else{
                    mView.setProductsDataFromBd(results);
                }
            });
        }
    }
}
