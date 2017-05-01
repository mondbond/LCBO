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
import com.lcbo.model.pojo.Stores.Pager;
import com.lcbo.model.pojo.Stores.Result;
import com.lcbo.model.pojo.Stores.Stores;
import com.lcbo.presenters.StoresPresenter;
import com.lcbo.util.Util;
import com.lcbo.view.LoadMoreListener;
import com.lcbo.view.StoresView;
import com.lcbo.view.activity.MainActivity;
import com.lcbo.view.adapters.StoresAdapter;
import com.lcbo.view.dialog.StoreSearchDialogFragment;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchStoresFragment extends BaseFragment implements StoresView, LoadMoreListener{

    public static final int SEARCH_STORE = 3;
    private final String SEARCH_STORE_DIALOG = "searchStoreDialog";

    private Pager mPager;
    private String mCityName = "";
    private String mFilter = "";
    @Inject
    StoresPresenter mPresenter;
    private StoreSearchDialogFragment mSearchDialog;
    private RecyclerView mRecycler;
    private StoresAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
        mPresenter.init(this);
        if(mAdapter == null){
            ((MainActivity) getActivity()).showLoadingAnimation();
            try {
                mPresenter.getStoresByCityAndFilter(mCityName, mFilter, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_stores, container, false);

        setRetainInstance(true);
        setHasOptionsMenu(true);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar11);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        mRecycler = (RecyclerView) v.findViewById(R.id.stores_search_fragment_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchDialog = new StoreSearchDialogFragment();
        mSearchDialog.setTargetFragment(SearchStoresFragment.this, 0);
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
                case SEARCH_STORE:
                    if(mAdapter!= null && !mFilter.equals(data.getStringExtra(StoreSearchDialogFragment.FILTER))){
                        mAdapter.getmStoresData().getResult().clear();
                        mAdapter.notifyDataSetChanged();
                    }
                    mFilter = data.getStringExtra(StoreSearchDialogFragment.FILTER);

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
                mSearchDialog.show(getActivity().getSupportFragmentManager(), SEARCH_STORE_DIALOG);
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
                if(mAdapter!= null){
                    mAdapter.getmStoresData().getResult().clear();
                }
                try {
                        mPresenter.getStoresByCityAndFilter(Util.makeCapitalLetter(mCityName), mFilter, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                return false;
        }

            @Override
            public boolean onQueryTextChange(String newText) {
                mCityName = newText;
                return false;
            }
        });
    }

    @Override
    public void setStoresData(Stores stores) {
        ((MainActivity) getActivity()).hideLoadingAnimation();

        mPager = stores.getPager();
        if(mAdapter != null){
            mAdapter.bindScrollDownListener(mRecycler);
        }
        if(mAdapter == null) {
            mAdapter = new StoresAdapter(stores, mRecycler, this, getActivity());
            mRecycler.setAdapter(mAdapter);
        }else {
            mAdapter.setLoadingMode(false);
            Stores newStores = mAdapter.getmStoresData();
            newStores.getResult().addAll(stores.getResult());
            mAdapter.setmStoresData(newStores);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setStoresDataFromBd(ArrayList<Result> results) {
        ((MainActivity) getActivity()).hideLoadingAnimation();
        Stores dbStores = new Stores();
        dbStores.setResult(results);
        mAdapter = new StoresAdapter(dbStores, mRecycler, this, getActivity());
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
            try {
                mPresenter.getStoresByCityAndFilter(mCityName, mFilter, mPager.getCurrentPage() + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
