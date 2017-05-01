package com.lcbo.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcbo.R;
import com.lcbo.model.pojo.Stores.Stores;
import com.lcbo.view.EndScrollDownListener;
import com.lcbo.view.LoadMoreListener;
import com.lcbo.view.activity.StoreDetailActivity;

public class StoresAdapter extends RecyclerView.Adapter {

    private Stores mStoresData;
    private LoadMoreListener mLoadMoreListener;
    private Context mContext;
    private EndScrollDownListener mScrollDownListener;
    private RecyclerView mRecyclerView;

    public StoresAdapter(Stores mStoresData, RecyclerView recyclerView, LoadMoreListener loadMoreListener,
                         Context context) {
        this.mStoresData = mStoresData;
        mLoadMoreListener = loadMoreListener;
        mContext = context;
        mScrollDownListener = new EndScrollDownListener(mLoadMoreListener);
        mRecyclerView = recyclerView;
        mRecyclerView.setOnScrollListener(mScrollDownListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;

        TextView storeName = (TextView) view.findViewById(R.id.store_search_fragment_name);
        TextView storeCity = (TextView) view.findViewById(R.id.store_search_fragment_city_name);
        TextView storeAdress = (TextView) view.findViewById(R.id.store_search_fragment_addres);

        storeName.setText(mStoresData.getResult().get(position).getName());
        storeCity.setText(mStoresData.getResult().get(position).getCity());
        storeAdress.setText(mStoresData.getResult().get(position).getAddressLine1());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StoreDetailActivity.class);
                intent.putExtra(StoreDetailActivity.STORE_DETAIL, mStoresData.getResult().get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStoresData.getResult().size();
    }

    public Stores getmStoresData() {
        return mStoresData;
    }

    public void setmStoresData(Stores mStoresData) {
        this.mStoresData = mStoresData;
    }

    public void setLoadingMode(boolean mode){
        mScrollDownListener.setLoadingMode(mode);
    }

    public void bindScrollDownListener(RecyclerView recyclerView){
        mRecyclerView = recyclerView;
        mRecyclerView.setOnScrollListener(mScrollDownListener);
    }

    public void unBindScrollDownListener(){
        mRecyclerView.setOnScrollListener(null);
    }
}
