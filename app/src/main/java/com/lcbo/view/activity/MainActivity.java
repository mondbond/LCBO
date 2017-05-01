package com.lcbo.view.activity;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.lcbo.App;
import com.lcbo.R;
import com.lcbo.common.BaseActivity;
import com.lcbo.common.IHasComponent;
import com.lcbo.di.AppComponent;
import com.lcbo.di.DaggerMainComponent;
import com.lcbo.di.MainComponent;
import com.lcbo.view.fragments.AboutFragment;
import com.lcbo.view.fragments.CartFragment;
import com.lcbo.view.fragments.ProductTypeFragment;
import com.lcbo.view.fragments.SearchProductsFragment;
import com.lcbo.view.fragments.SearchStoresFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IHasComponent<MainComponent> {

    private final String SAVE_LAST_FRAGMENT = "mLastFragment";
    private final String ABOUT_FRAGMENT_TAG = "aboutFragmentTag";
    private final String SEARCH_STORE_FRAGMENT_TAG = "searchStoreFragmentTag";
    private final String SEARCH_PRODUCTS_FRAGMENT_TAG = "searchProductsFragmentTag";
    private final String PRODUCT_CATEGORY_FRAGMENT_TAG = "productCategoryFragmentTag";
    private final String CART_FRAGMENT_TAG = "cartFragmentTag";

    private String mLastFragment = SEARCH_STORE_FRAGMENT_TAG;
    private static MainComponent sComponent;
    private SearchStoresFragment mSearchStoresFragment;
    private SearchProductsFragment mSearchProductsFragment;
    private ProductTypeFragment mProductTypeFragment;
    private CartFragment mCartFragment;
    private AboutFragment mAboutFragment;
    private View mLoadingCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingCircle = findViewById(R.id.progress_circle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//      define last showed fragment
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSearchStoresFragment =  ( SearchStoresFragment) getSupportFragmentManager().findFragmentByTag(SEARCH_STORE_FRAGMENT_TAG);
        if(mSearchStoresFragment == null){
            mSearchStoresFragment = new SearchStoresFragment();
        }

        mSearchProductsFragment =  ( SearchProductsFragment) getSupportFragmentManager().findFragmentByTag(SEARCH_PRODUCTS_FRAGMENT_TAG);
        if(mSearchProductsFragment == null){
            mSearchProductsFragment = new SearchProductsFragment();
        }

        mProductTypeFragment =  ( ProductTypeFragment) getSupportFragmentManager().findFragmentByTag(PRODUCT_CATEGORY_FRAGMENT_TAG);
        if(mProductTypeFragment == null){
            mProductTypeFragment = new ProductTypeFragment();
        }

        mCartFragment =  ( CartFragment) getSupportFragmentManager().findFragmentByTag(CART_FRAGMENT_TAG);
        if(mCartFragment == null){
            mCartFragment = new CartFragment();
        }

        mAboutFragment =  ( AboutFragment) getSupportFragmentManager().findFragmentByTag(ABOUT_FRAGMENT_TAG);
        if(mAboutFragment == null){
            mAboutFragment = new AboutFragment();
        }

        if (savedInstanceState != null) {
            mLastFragment = savedInstanceState.getString(SAVE_LAST_FRAGMENT);
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, getSupportFragmentManager().findFragmentByTag(mLastFragment));
            fragmentTransaction.commit();
        }else {
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, mSearchStoresFragment, SEARCH_STORE_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setupComponent(AppComponent appComponent) {
        sComponent = DaggerMainComponent.builder()
                .appComponent(App.getAppComponent())
                .build();
        sComponent.inject(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_products_search) {
            ft.replace(R.id.main_fragment_container, mSearchProductsFragment, SEARCH_PRODUCTS_FRAGMENT_TAG);
            mLastFragment = SEARCH_PRODUCTS_FRAGMENT_TAG;
        } else if (id == R.id.nav_products_categories) {
            ft.replace(R.id.main_fragment_container, mProductTypeFragment, PRODUCT_CATEGORY_FRAGMENT_TAG);
            mLastFragment = PRODUCT_CATEGORY_FRAGMENT_TAG;
        } else if (id == R.id.nav_cart) {
            ft.replace(R.id.main_fragment_container, mCartFragment, CART_FRAGMENT_TAG);
            mLastFragment = CART_FRAGMENT_TAG;
        } else if (id == R.id.nav_stores_search) {
            ft.replace(R.id.main_fragment_container, mSearchStoresFragment, SEARCH_STORE_FRAGMENT_TAG);
            mLastFragment = SEARCH_STORE_FRAGMENT_TAG;
        }else if(id == R.id.nav_about){
            ft.replace(R.id.main_fragment_container, mAboutFragment, ABOUT_FRAGMENT_TAG);
            mLastFragment = ABOUT_FRAGMENT_TAG;
        }
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void hideLoadingAnimation() {
        mLoadingCircle.setAnimation(null);
        mLoadingCircle.setVisibility(View.INVISIBLE);
    }

    public void showLoadingAnimation() {
        Animation progressAnimation = AnimationUtils.loadAnimation(this, R.anim.loading_rotation);
        mLoadingCircle.setVisibility(View.VISIBLE);
        mLoadingCircle.startAnimation(progressAnimation);
    }

    @Override
    public MainComponent getComponent() {
        return sComponent;
    }
    public static MainComponent getMainComponent() {
        return sComponent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_LAST_FRAGMENT, mLastFragment);
    }
}
