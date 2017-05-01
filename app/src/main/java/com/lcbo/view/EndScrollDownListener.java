package com.lcbo.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class EndScrollDownListener extends RecyclerView.OnScrollListener {

    private final int DIFFERANCE = 5;

    private LoadMoreListener mLoadMoreListener;
    private boolean mLoadingMode;

    public EndScrollDownListener(LoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(!mLoadingMode){
            LinearLayoutManager layoutManager =(LinearLayoutManager) recyclerView.getLayoutManager();
            int totalItemCount = layoutManager.getItemCount();
            int lastVisibleItemCount = layoutManager.findLastVisibleItemPosition();
            if(totalItemCount - lastVisibleItemCount <= DIFFERANCE) {
            mLoadMoreListener.loadMore();
            }
        }
    }

    public void setLoadingMode(boolean mLoadingMode) {
        this.mLoadingMode = mLoadingMode;
    }
}
