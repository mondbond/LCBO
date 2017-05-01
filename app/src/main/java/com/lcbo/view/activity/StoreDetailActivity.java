package com.lcbo.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lcbo.R;
import com.lcbo.common.BaseActivity;
import com.lcbo.di.AppComponent;
import com.lcbo.model.pojo.Products.Pager;
import com.lcbo.model.pojo.Products.StoreProducts;
import com.lcbo.model.pojo.Stores.Result;
import com.lcbo.presenters.DetailStorePresenter;
import com.lcbo.util.Util;
import com.lcbo.view.LoadMoreListener;
import com.lcbo.view.DetailStoreView;
import com.lcbo.view.adapters.ProductAdapter;
import com.lcbo.view.dialog.ChoosePnoneNumberDialogFragment;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class StoreDetailActivity extends BaseActivity implements OnMapReadyCallback, DetailStoreView,
        LoadMoreListener {

    public static final String STORE_DETAIL = "storeDetail";
    private static final String KEY_RECYCLER_STATE = "recycler";
    private GoogleMap mMap;
    private Result mStore;
    private RecyclerView mRecycler;
    private ProductAdapter mAdapter;
    private ChoosePnoneNumberDialogFragment mPhoneNumberDialog =  new ChoosePnoneNumberDialogFragment();
    private Pager mPager;
    private View mLoadingCircle;
    private Bundle mBundleRecyclerViewState;

    @Inject
    DetailStorePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLoadingCircle = findViewById(R.id.detail_activity_progress_circle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mStore = bundle.getParcelable(STORE_DETAIL);
        mRecycler = (RecyclerView) findViewById(R.id.detail_activity_products_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.init(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecycler.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    @Override
    public void setupComponent(AppComponent appComponent) {
        MainActivity.getMainComponent().inject(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng storeLocation = new LatLng(mStore.getLatitude(), mStore.getLongitude());
        mMap.addMarker(new MarkerOptions().position(storeLocation).title("There we are !"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(storeLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_store, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.call) {
            if(Util.getTwoPhoneNumbers(mStore.getTelephone())!= null) {
                mPhoneNumberDialog.show(getSupportFragmentManager(), "fff");
            }else {
                makeCall(mStore.getTelephone());
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setProductsData(StoreProducts storeProducts) {
        hideLoadingAnimation();
        mPager = storeProducts.getPager();

        if(mAdapter != null){
            mAdapter.bindScrollDownListener(mRecycler);
        }
        if(mAdapter == null) {
            mAdapter = new ProductAdapter(storeProducts, mRecycler, this, this);
            mRecycler.setAdapter(mAdapter);
        }else if(!mPager.isIsFirstPage()) {
            mAdapter.setLoadingMode(false);
            StoreProducts newProducts = mAdapter.getProductsData();
            newProducts.getResult().addAll(storeProducts.getResult());
            mAdapter.setProductsData(newProducts);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            mRecycler.getLayoutManager().onRestoreInstanceState(listState);
        }else {
            showLoadingAnimation();
            try {
                mPresenter.getProductsDataByStore(String.valueOf(mStore.getId()), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setProductsDataFromDb(List<com.lcbo.model.pojo.Products.Result> results) {
        hideLoadingAnimation();
        StoreProducts dbStores = new StoreProducts();
        dbStores.setResult(results);
        mAdapter = new ProductAdapter(dbStores, mRecycler, this, this);
        mAdapter.unBindScrollDownListener();
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMore() {
        if(mPager.getCurrentPage() != mPager.getTotalPages()) {
            showLoadingAnimation();
            mAdapter.setLoadingMode(true);
            try {
                mPresenter.getProductsDataByStore(String.valueOf(mStore.getId()),  mPager.getCurrentPage() + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void makeCall(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }
}
