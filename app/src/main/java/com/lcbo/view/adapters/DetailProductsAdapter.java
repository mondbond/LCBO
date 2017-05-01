package com.lcbo.view.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcbo.R;
import com.lcbo.model.pojo.Products.StoreProducts;
import com.lcbo.view.EndScrollDownListener;
import com.lcbo.view.LoadMoreListener;
import com.lcbo.util.Util;
import com.lcbo.view.dialog.ChooseProductCountDialogFragment;
import com.lcbo.view.dialog.ProductDetailDialogFragment;
import com.squareup.picasso.Picasso;

public class DetailProductsAdapter extends RecyclerView.Adapter{

    private StoreProducts mStoresProducts;
    private LoadMoreListener mLoadMoreListener;
    private Context mContext;
    private EndScrollDownListener mScrollDownListener;
    private ProductDetailDialogFragment mDetailDialog;
    private ChooseProductCountDialogFragment mChooseProductCountDialog;
    private RecyclerView mRecyclerView;

    public DetailProductsAdapter(StoreProducts storeProducts, RecyclerView recyclerView, LoadMoreListener loadMoreListener,
                                 Context context) {
        mStoresProducts = storeProducts;
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
                .inflate(R.layout.add_to_cart_product_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;

        ImageView productImage = (ImageView) view.findViewById(R.id.add_to_cart_product_img);
        TextView productName = (TextView) view.findViewById(R.id.add_to_cart_product_item_name);
        TextView productMl = (TextView) view.findViewById(R.id.add_to_cart_product_ml);
        TextView productSecondaryCategory = (TextView) view.findViewById(R.id.add_to_cart_product_secondary_category);
        TextView productPrice = (TextView) view.findViewById(R.id.add_to_cart_product_prise);
        ImageView addToCartBtn = (ImageView) view.findViewById(R.id.add_to_cart_product_btn);

        Picasso.with(mContext).load(mStoresProducts.getResult().get(position).getImageThumbUrl()).into(productImage);
        productName.setText(mStoresProducts.getResult().get(position).getName());
        productMl.setText(String.valueOf(mStoresProducts.getResult().get(position).getPackage()));
        productSecondaryCategory.setText(mStoresProducts.getResult().get(position).getSecondaryCategory());
        productPrice.setText(Util.formatToDollar(((float)mStoresProducts.getResult().get(position).getPriceInCents())));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDetailDialog == null){
                    mDetailDialog = new ProductDetailDialogFragment();
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable(ProductDetailDialogFragment.DETAIL_PRODUCT, mStoresProducts.getResult().get(position));
                mDetailDialog.setArguments(bundle);
                mDetailDialog.show(((AppCompatActivity) mContext).getFragmentManager(), "dsffsdf");
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mChooseProductCountDialog == null){
                    mChooseProductCountDialog = new ChooseProductCountDialogFragment();
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable(ProductDetailDialogFragment.DETAIL_PRODUCT, mStoresProducts.getResult().get(position));
                mChooseProductCountDialog.setArguments(bundle);
                mChooseProductCountDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "dsffsdf");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStoresProducts.getResult().size();
    }

    public StoreProducts getProductsData() {
        return mStoresProducts;
    }

    public void setProductsData(StoreProducts mStoresData) {
        mStoresProducts = mStoresData;
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
