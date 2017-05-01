package com.lcbo.view.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcbo.R;
import com.lcbo.common.BaseFragment;
import com.lcbo.di.MainComponent;
import com.lcbo.model.db.Cart;
import com.lcbo.model.pojo.Products.Result;
import com.lcbo.presenters.CartPresenter;
import com.lcbo.util.Util;
import com.lcbo.view.CartView;
import com.lcbo.view.adapters.CartAdapter;
import com.lcbo.view.dialog.ChooseProductCountDialogFragment;
import com.lcbo.view.dialog.ProductDetailDialogFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment implements CartView, CartAdapter.CartListener {

    @Inject
    CartPresenter mPresenter;
    private RecyclerView mRecycler;
    private CartAdapter mAdapter;
    private TextView mTotalCost;
    private ChooseProductCountDialogFragment mChooseProductCountDialogFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
        mPresenter.init(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        setRetainInstance(true);

        mTotalCost = (TextView) v.findViewById(R.id.total_cost_data);
        mRecycler = (RecyclerView) v.findViewById(R.id.cart_list);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mChooseProductCountDialogFragment = new ChooseProductCountDialogFragment();
        mChooseProductCountDialogFragment.setTargetFragment(CartFragment.this, 0);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getAllProductsFromCart();
    }

    @Override
    public void setProductsFromCarts(List<Cart> carts) {
        int totalCost = 0;
        for (Cart item: carts){
            totalCost += item.getCost()*item.getCount();
        }
        mTotalCost.setText(Util.formatToDollar((float) totalCost));
        if (mAdapter == null) {
            mAdapter = new CartAdapter(carts, getActivity(), this);
            mRecycler.setAdapter(mAdapter);
        } else {
            mAdapter.setmCarts(carts);
            mRecycler.setAdapter(mAdapter);
        }
    }

    @Override
    public void onChangeCount(Cart cart) {
        Result result = new Result();
        result.setName(cart.getProductName());
        result.setId((int) (long) cart.getProductId());
        result.setPackage(cart.getProductMl());
        result.setPriceInCents(cart.getCost());

        Bundle bundle = new Bundle();
        bundle.putParcelable(ProductDetailDialogFragment.DETAIL_PRODUCT, result);
        mChooseProductCountDialogFragment.setArguments(bundle);
        mChooseProductCountDialogFragment.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "dsaf");
    }

    @Override
    public void onDeleteCart(Cart cart) {
        mPresenter.deleteCartById((long) cart.getId());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ChooseProductCountDialogFragment.CART_UPDATE:
                    Result newResult = data.getParcelableExtra(ChooseProductCountDialogFragment.NEW_CART);
                    mPresenter.editProduct(newResult, data.getIntExtra(ChooseProductCountDialogFragment.NEW_COUNT, 0));
            }
        }
    }
}
