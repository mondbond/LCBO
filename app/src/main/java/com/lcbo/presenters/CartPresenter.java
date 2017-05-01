package com.lcbo.presenters;

import com.lcbo.common.BasePresenter;
import com.lcbo.model.DbModel;
import com.lcbo.model.pojo.Products.Result;
import com.lcbo.view.CartView;

import javax.inject.Inject;

public class CartPresenter implements BasePresenter<CartView> {

    private CartView mView;
    private DbModel mDbModel;

    @Inject
    public CartPresenter(DbModel mDbModel) {
        this.mDbModel = mDbModel;
    }

    @Override
    public void init(CartView view) {
        this.mView = view;
    }

    public void addProductToCart(Result product, int count, boolean isReturn){
        mDbModel.addOrUpdateProductToCart(product, count).subscribe(res ->{
            if(isReturn){
                getAllProductsFromCart();
            }
        });
    }

    public void editProduct(Result product, int newCount){
        mDbModel.editProduct(product, newCount).subscribe(res ->{
            getAllProductsFromCart();
        });
    }

    public void getAllProductsFromCart(){
        mDbModel.getAllProductsFromCart().subscribe(carts -> {
           mView.setProductsFromCarts(carts);
        });
    }

    public void deleteCartById(long id) {
        mDbModel.deleteCartItemById(id).subscribe(res ->{
            getAllProductsFromCart();
        });
    }
}
