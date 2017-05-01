package com.lcbo.view.dialog;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.lcbo.R;
import com.lcbo.util.Util;
import com.lcbo.view.fragments.SearchStoresFragment;

import java.util.LinkedHashSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreSearchDialogFragment extends DialogFragment {

    public static final String FILTER = "filter";
    private Button mSearchBtn;
    private LinkedHashSet<String> mFilter = new LinkedHashSet<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_store_search_dialog, container, false);

        View.OnClickListener checkBoxListener = new View.OnClickListener() {
            @Override
            public void onClick(View checkBox) {
                String textForServer = "";
                switch(checkBox.getId()){
                    case R.id.store_search_dialog_filter_has_parking:
                        textForServer = new String("has_parking");
                        break;
                    case R.id.store_search_dialog_filter_vintage_corner:
                        textForServer = new String("has_vintages_corner");
                        break;
                    case R.id.store_search_dialog_filter_special_occasion:
                        textForServer = new String("has_special_occasion_permits");
                        break;
                    case R.id.store_search_dialog_filter_cold_beer:
                        textForServer = new String("has_beer_cold_room");
                        break;
                    case R.id.store_search_dialog_filter_tasting_bar:
                        textForServer = new String("has_tasting_bar");
                        break;
                    case R.id.store_search_dialog_filter_consultant:
                        textForServer = new String("has_product_consultant");
                        break;
                    case R.id.store_search_dialog_filter_beelingual_service:
                        textForServer = new String("has_bilingual_services");
                        break;
                    case R.id.store_search_dialog_filter_wheels:
                        textForServer = new String("has_wheelchair_accessability");
                        break;
                    case R.id.store_search_dialog_filter_public_transit:
                        textForServer = new String("has_transit_access");
                        break;
                    default:
                        break;
                }
                if(((CheckBox) checkBox).isChecked()){
                    mFilter.add(textForServer);
                }else if (!((CheckBox) checkBox).isChecked() && mFilter.contains(textForServer)){
                    mFilter.remove(textForServer);
                }
            }
        };
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        mSearchBtn = (Button) v.findViewById(R.id.store_search_dialog_find_btn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(FILTER, Util.makeFilterInString(mFilter));
                FragmentManager fm = getFragmentManager();
                fm.findFragmentById(R.id.main_fragment_container).onActivityResult(SearchStoresFragment.SEARCH_STORE, Activity.RESULT_OK,
                            intent);
                dismiss();
            }
        });

        CheckBox hasParking = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_has_parking);
        hasParking.setOnClickListener(checkBoxListener);
        CheckBox hasTransit = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_public_transit);
        hasTransit.setOnClickListener(checkBoxListener);
        CheckBox hasVintage = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_vintage_corner);
        hasVintage.setOnClickListener(checkBoxListener);
        CheckBox hasTastingBar = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_tasting_bar);
        hasTastingBar.setOnClickListener(checkBoxListener);
        CheckBox hasConsultant = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_consultant);
        hasConsultant.setOnClickListener(checkBoxListener);
        CheckBox hasOccasion = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_special_occasion);
        hasOccasion.setOnClickListener(checkBoxListener);
        CheckBox hasBilingualService = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_beelingual_service);
        hasBilingualService.setOnClickListener(checkBoxListener);
        CheckBox hasWheel = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_wheels);
        hasWheel.setOnClickListener(checkBoxListener);
        CheckBox hasColdBeerRoom = (CheckBox) v.findViewById(R.id.store_search_dialog_filter_cold_beer);
        hasColdBeerRoom.setOnClickListener(checkBoxListener);

        return v;
    }
}
