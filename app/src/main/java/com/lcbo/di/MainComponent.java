package com.lcbo.di;

import com.lcbo.view.activity.MainActivity;
import com.lcbo.view.activity.StoreDetailActivity;
import com.lcbo.view.dialog.ChooseProductCountDialogFragment;
import com.lcbo.view.fragments.CartFragment;
import com.lcbo.view.fragments.ProductTypeFragment;
import com.lcbo.view.fragments.SearchProductsFragment;
import com.lcbo.view.fragments.SearchStoresFragment;

import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {
    void inject(StoreDetailActivity storeDetailActivity);
    void inject(CartFragment cartFragment);
    void inject(MainActivity mainActivity);
    void inject(SearchStoresFragment searchStoresFragment);
    void inject(ProductTypeFragment.PlaceholderFragment productTypeFragmentPlaceHolder);
    void inject(SearchProductsFragment searchProductsFragment);
    void inject(ChooseProductCountDialogFragment chooseProductCountDialogFragment);
}
