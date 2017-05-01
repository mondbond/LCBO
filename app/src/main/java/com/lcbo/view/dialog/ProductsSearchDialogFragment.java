package com.lcbo.view.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import com.lcbo.R;
import com.lcbo.util.Util;
import com.lcbo.view.fragments.SearchProductsFragment;
import java.util.LinkedHashSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsSearchDialogFragment extends DialogFragment {

    public static final String PRODUCTS_NAME = "citName";
    public static final String FILTER = "filter";
    private Button mSearchBtn;
    private LinkedHashSet<String> mFilter = new LinkedHashSet<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_products_search_dialog, container, false);
        getTargetFragment().getId();

        View.OnClickListener checkBoxListener = new View.OnClickListener() {
            @Override
            public void onClick(View checkBox) {
                String textForServer = "";
                switch(checkBox.getId()){
                    case R.id.product_search_is_discontinued_filter:
                        textForServer = new String("is_discontinued");
                        break;
                    case R.id.store_search_dialog_filter_vintage_corner:
                        textForServer = new String("has_value_added_promotion");
                        break;
                    case R.id.product_search_limited_time_offer_filter:
                        textForServer = new String("has_limited_time_offer");
                        break;
                    case R.id.product_search_has_bonus_reward_filter:
                        textForServer = new String("has_bonus_reward_miles");
                        break;
                    case R.id.product_search_is_seasonal_filter:
                        textForServer = new String("is_seasonal");
                        break;
                    case R.id.product_search_is_vga_filter:
                        textForServer = new String("is_vqa");
                        break;
                    case R.id.product_search_is_ocb_filter:
                        textForServer = new String("is_ocb");
                        break;
                    case R.id.product_search_is_kosher_filter:
                        textForServer = new String("is_kosher");
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
        mSearchBtn = (Button) v.findViewById(R.id.products_search_dialog_find_btn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(FILTER, Util.makeFilterInString(mFilter));
                getTargetFragment().onActivityResult(SearchProductsFragment.SEARCH_PRODUCT, Activity.RESULT_OK,
                        intent);
                dismiss();
            }
        });

        CheckBox hasParking = (CheckBox) v.findViewById(R.id.product_search_is_discontinued_filter);
        hasParking.setOnClickListener(checkBoxListener);
        CheckBox hasTransit = (CheckBox) v.findViewById(R.id.product_search_is_has_promotion_filter);
        hasTransit.setOnClickListener(checkBoxListener);
        CheckBox hasVintage = (CheckBox) v.findViewById(R.id.product_search_limited_time_offer_filter);
        hasVintage.setOnClickListener(checkBoxListener);
        CheckBox hasTastingBar = (CheckBox) v.findViewById(R.id.product_search_has_bonus_reward_filter);
        hasTastingBar.setOnClickListener(checkBoxListener);
        CheckBox hasConsultant = (CheckBox) v.findViewById(R.id.product_search_is_seasonal_filter);
        hasConsultant.setOnClickListener(checkBoxListener);
        CheckBox hasOccasion = (CheckBox) v.findViewById(R.id.product_search_is_vga_filter);
        hasOccasion.setOnClickListener(checkBoxListener);
        CheckBox hasBilingualService = (CheckBox) v.findViewById(R.id.product_search_is_ocb_filter);
        hasBilingualService.setOnClickListener(checkBoxListener);
        CheckBox hasWheel = (CheckBox) v.findViewById(R.id.product_search_is_kosher_filter);
        hasWheel.setOnClickListener(checkBoxListener);

        return v;
    }
}
