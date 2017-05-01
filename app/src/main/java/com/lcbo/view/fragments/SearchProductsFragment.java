package com.lcbo.view.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.lcbo.R;
import com.lcbo.common.BaseFragment;
import com.lcbo.di.MainComponent;
import com.lcbo.model.pojo.Products.Result;
import com.lcbo.model.pojo.Products.StoreProducts;
import com.lcbo.model.pojo.Products.Pager;
import com.lcbo.presenters.ProductsPresenter;
import com.lcbo.util.Util;
import com.lcbo.view.LoadMoreListener;
import com.lcbo.view.activity.MainActivity;
import com.lcbo.view.ProductsView;
import com.lcbo.view.adapters.ProductAdapter;
import com.lcbo.view.dialog.ProductsSearchDialogFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchProductsFragment extends BaseFragment implements ProductsView, LoadMoreListener {

    public static final int SEARCH_PRODUCT = 24;

    private final String SEARCH_PRODUCTS_DIALOG = "searchDialog";
    private Pager mPager;
    private String mProductName = "";
    private String mFilter = "";
    @Inject
    ProductsPresenter mPresenter;
    private ProductsSearchDialogFragment mSearchDialog;
    private RecyclerView mRecycler;
    private ProductAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
        mPresenter.init(this);
        if(mAdapter == null){
            ((MainActivity) getActivity()).showLoadingAnimation();
                mPresenter.getProductsByNameAndFilters(mProductName, mFilter, 1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_products, container, false);

        setHasOptionsMenu(true);
        setRetainInstance(true);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar111);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        mRecycler = (RecyclerView) v.findViewById(R.id.products_search_fragment_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchDialog = new ProductsSearchDialogFragment();
        mSearchDialog.setTargetFragment(SearchProductsFragment.this, 0);
        if(mAdapter != null){
            mAdapter.bindScrollDownListener(mRecycler);
        }

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mAdapter != null){
            mRecycler.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mAdapter.bindScrollDownListener(mRecycler);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SEARCH_PRODUCT:
                    if(mAdapter!= null && !mFilter.equals(data.getStringExtra(ProductsSearchDialogFragment.FILTER))){
                        mAdapter.getProductsData().getResult().clear();
                        mAdapter.notifyDataSetChanged();
                    }
                    mFilter = data.getStringExtra(ProductsSearchDialogFragment.FILTER);

                    break;
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.store_search_menu, menu);
        ImageView imageView = (ImageView) getView().findViewById(R.id.search_store_filter);
        MenuItem item = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) item.getActionView();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchDialog.show(getActivity().getSupportFragmentManager(), SEARCH_PRODUCTS_DIALOG);
            }
        });


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                imageView.setVisibility(View.GONE);
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setVisibility(View.VISIBLE);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(mAdapter != null){
                    mAdapter.getProductsData().getResult().clear();
                }
                mPresenter.getProductsByNameAndFilters(Util.makeCapitalLetter(mProductName), mFilter, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mProductName = newText;
                return false;
            }
        });
    }

    @Override
    public void setProductsData(StoreProducts products) {
        ((MainActivity) getActivity()).hideLoadingAnimation();
        mPager = products.getPager();
        if(mAdapter != null){
            mAdapter.bindScrollDownListener(mRecycler);
        }
        if(mAdapter == null) {
            mAdapter = new ProductAdapter(products, mRecycler, this, getActivity());
            mRecycler.setAdapter(mAdapter);
        }else {
            mAdapter.setLoadingMode(false);
            StoreProducts newProducts = mAdapter.getProductsData();
            newProducts.getResult().addAll(products.getResult());
            mAdapter.setProductsData(newProducts);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setProductsDataFromBd(List<Result> results) {
        ((MainActivity) getActivity()).hideLoadingAnimation();
        StoreProducts dbStores = new StoreProducts();
        dbStores.setResult(results);
        mAdapter = new ProductAdapter(dbStores, mRecycler, this, getActivity());
        mAdapter.unBindScrollDownListener();
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMore () {
        if(mPager.getCurrentPage() != mPager.getTotalPages()) {
            ((MainActivity) getActivity()).showLoadingAnimation();
            mAdapter.setLoadingMode(true);
            mPresenter.getProductsByNameAndFilters(mProductName, mFilter, mPager.getCurrentPage() + 1 );
        }
    }
}
