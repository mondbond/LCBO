package com.lcbo.presenters;

import android.content.Context;
import com.lcbo.R;
import com.lcbo.common.BasePresenter;
import com.lcbo.model.DbModel;
import com.lcbo.model.NetworkData;
import com.lcbo.util.Util;
import com.lcbo.view.StoresView;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Retrofit;

public class StoresPresenter implements BasePresenter<StoresView> {

    private StoresView mView;
    private NetworkData mNetwork;
    private Retrofit mRetrofit;
    private DbModel mDbModel;
    private Context mContext;

    @Inject
    public StoresPresenter(NetworkData networkData, @Named("lcboAPI") Retrofit retrofit, DbModel
                           dbModel, Context context) {
        mNetwork = networkData;
        mRetrofit = retrofit;
        mDbModel = dbModel;
        mContext = context;
    }

    @Override
    public void init(StoresView view) {
        mView = view;
    }

    public void getStoresByCityAndFilter(String citName, String filter, int page) throws IOException {

        if (Util.isNetworkAvailable(mContext)) {
            mNetwork.getStoresByCityNameAndFilter(mRetrofit, citName, filter, page).subscribe(stores ->{
                mView.setStoresData(stores);
                mDbModel.addNewStores(stores).subscribe(res ->{
                });
            });
        }else {
            mDbModel.getAllStoresByNameAndFilter(citName, filter).subscribe(results -> {
                if(results.isEmpty()){
                    mView.showErrorMessage(mContext.getResources().getString(R.string.turn_on_network_connection_error_msg));
                }else{
                    mView.setStoresDataFromBd(results);
                }
            });
        }
    }
}
