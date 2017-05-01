package com.lcbo.view.dialog;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcbo.R;
import com.lcbo.model.pojo.Products.Result;
import com.lcbo.util.Util;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailDialogFragment extends DialogFragment {

    public static final String DETAIL_PRODUCT = "detail_product";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View v = inflater.inflate(R.layout.fragment_product_detail_dialog, container, false);

        Result result = getArguments().getParcelable(DETAIL_PRODUCT);

        ImageView productImage = (ImageView) v.findViewById(R.id.detail_product_img);
        TextView productName = (TextView) v.findViewById(R.id.detail_product_name);
        TextView productSecondaryCategory = (TextView) v.findViewById(R.id.detail_product_secondary_category);
        TextView tastingNote = (TextView) v.findViewById(R.id.detail_product_tasting_note);
        TextView productOrigin = (TextView) v.findViewById(R.id.detail_product_origin);
        TextView productMl = (TextView) v.findViewById(R.id.detail_product_ml);
        TextView productCost = (TextView) v.findViewById(R.id.detail_product_cost);

        Picasso.with(getActivity()).load(result.getImageThumbUrl()).into(productImage);
        productName.setText(result.getName());
        productSecondaryCategory.setText(result.getSecondaryCategory());
        productOrigin.setText(result.getProducerName());
        if(result.getTastingNote() != null) {
            tastingNote.setText(result.getTastingNote().toString());
        }else {
            tastingNote.setText("No description");
        }
        productMl.setText(result.getPackage());
        productCost.setText(Util.formatToDollar(result.getPriceInCents()));
        return v;
    }
}
