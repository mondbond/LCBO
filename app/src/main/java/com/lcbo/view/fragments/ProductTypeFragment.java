package com.lcbo.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lcbo.R;
import com.lcbo.common.BaseFragment;
import com.lcbo.di.MainComponent;
import com.lcbo.model.pojo.Products.Pager;
import com.lcbo.model.pojo.Products.Result;
import com.lcbo.model.pojo.Products.StoreProducts;
import com.lcbo.presenters.ProductsPresenter;
import com.lcbo.view.LoadMoreListener;
import com.lcbo.view.activity.MainActivity;
import com.lcbo.view.ProductsView;
import com.lcbo.view.adapters.DetailProductsAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductTypeFragment extends Fragment {

    public static final String BEER = "Beer";
    public static final String WINE = "Wine";
    public static final String SPIRIT = "Spirits";

    private ViewPager mViewPager;

    private ProductTypeFragment.PlaceholderFragment mBearPlaceHolder;
    private ProductTypeFragment.PlaceholderFragment mWinePlaceHolder;
    private ProductTypeFragment.PlaceholderFragment mSpiritPlaceHolder;

    private ProductTypeFragment.SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_type1, container, false);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        mSectionsPagerAdapter = new ProductTypeFragment.SectionsPagerAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) v.findViewById(R.id.container1);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(mViewPager);

        return v;
    }

    public static class PlaceholderFragment extends BaseFragment implements ProductsView, LoadMoreListener {

        private static final String PRODUCT_TYPE = "product_type";

        private Pager mPager;
        @Inject
        ProductsPresenter mPresenter;
        private DetailProductsAdapter mAdapter;
        private RecyclerView mRecycler;
        private String mProductType;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.getComponent(MainComponent.class).inject(this);
            mPresenter.init(this);
            Bundle bundle = getArguments();
            mProductType = bundle.getString(PRODUCT_TYPE);
            ((MainActivity) getActivity()).showLoadingAnimation();
            mPresenter.getProductsByCategory(mProductType, 1);
            if(mAdapter == null){
                ((MainActivity) getActivity()).showLoadingAnimation();
                mPresenter.getProductsByCategory(mProductType, 1);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_products_category, container, false);
            setRetainInstance(true);

            mRecycler = (RecyclerView) v.findViewById(R.id.products_category_recycler);
            mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        public void setProductsData(StoreProducts products) {
            ((MainActivity) getActivity()).hideLoadingAnimation();
            mPager = products.getPager();
            if(mAdapter != null){
                mAdapter.bindScrollDownListener(mRecycler);
            }
            if(mAdapter == null) {
                mAdapter = new DetailProductsAdapter(products, mRecycler, this, getActivity());
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
            mAdapter = new DetailProductsAdapter(dbStores, mRecycler, this, getActivity());
            mAdapter.unBindScrollDownListener();
            mRecycler.setAdapter(mAdapter);
        }

        @Override
        public void showErrorMessage(String msg) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        }

        @Override
        public void loadMore() {
            if(mPager.getCurrentPage() != mPager.getTotalPages()) {
                ((MainActivity) getActivity()).showLoadingAnimation();
                mAdapter.setLoadingMode(true);
                mPresenter.getProductsByCategory(mProductType, mPager.getCurrentPage() + 1);
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(mBearPlaceHolder == null || mWinePlaceHolder == null || mSpiritPlaceHolder == null) {
                mBearPlaceHolder = new ProductTypeFragment.PlaceholderFragment();
                mWinePlaceHolder = new ProductTypeFragment.PlaceholderFragment();
                mSpiritPlaceHolder = new ProductTypeFragment.PlaceholderFragment();
            }

            Bundle args = new Bundle();
            switch (position) {
                case 0:
                    args.putString(ProductTypeFragment.PlaceholderFragment.PRODUCT_TYPE, BEER);
                    mBearPlaceHolder.setArguments(args);
                    return mBearPlaceHolder;
                case 1:
                    args.putString(ProductTypeFragment.PlaceholderFragment.PRODUCT_TYPE, WINE);
                    mWinePlaceHolder.setArguments(args);
                    return mWinePlaceHolder;
                case 2:
                    args.putString(ProductTypeFragment.PlaceholderFragment.PRODUCT_TYPE, SPIRIT);
                    mSpiritPlaceHolder.setArguments(args);
                    return mSpiritPlaceHolder;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return BEER;
                case 1:
                    return WINE;
                case 2:
                    return SPIRIT;
            }
            return null;
        }
    }
}
