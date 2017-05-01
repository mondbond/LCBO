package com.lcbo.presenters;

import android.content.Context;

import com.lcbo.R;
import com.lcbo.common.BasePresenter;
import com.lcbo.model.DbModel;
import com.lcbo.model.NetworkData;
import com.lcbo.util.Util;
import com.lcbo.view.DetailStoreView;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Retrofit;

public class DetailStorePresenter implements BasePresenter<DetailStoreView> {

    private DetailStoreView mView;
    private NetworkData mNetwork;
    private Retrofit mRetrofit;
    private DbModel mDbModel;
    private Context mContext;

    @Inject
    public DetailStorePresenter(NetworkData mNetwork, @Named("lcboAPI") Retrofit retrofit, DbModel dbModel,
                                Context context) {
        this.mNetwork = mNetwork;
        this.mRetrofit = retrofit;
        this.mDbModel = dbModel;
        this.mContext = context;
    }

    @Override
    public void init(DetailStoreView view) {
        mView = view;
    }

    public void getProductsDataByStore(String storeId, int page) throws IOException {
        if (Util.isNetworkAvailable(mContext)) {
            mNetwork.getProductsDataByStore(mRetrofit, storeId, page).subscribe(storeProducts -> {
                mDbModel.addProductsRelationAndProductsToDb(storeProducts, Integer.parseInt(storeId)).subscribe();
                mView.setProductsData(storeProducts);
            });
        }else {
            mDbModel.getAllProductsByStore(Integer.parseInt(storeId)).subscribe(results ->{
                if(results.isEmpty()){
                    mView.showErrorMessage(mContext.getResources().getString(R.string.turn_on_network_connection_error_msg));
                }else{
                    mView.setProductsDataFromDb(results);
                }
                }
            );
        }
    }
}
