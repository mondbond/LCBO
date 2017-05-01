package com.lcbo.view.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lcbo.R;
import com.lcbo.common.BaseDialogFragment;
import com.lcbo.di.MainComponent;
import com.lcbo.model.db.Cart;
import com.lcbo.model.pojo.Products.Result;
import com.lcbo.presenters.CartPresenter;
import com.lcbo.util.InputFilterMinMax;
import com.lcbo.util.Util;
import com.lcbo.view.CartView;

import java.util.List;

import javax.inject.Inject;

import static com.lcbo.view.dialog.ProductDetailDialogFragment.DETAIL_PRODUCT;

/**
 * A simple {@link Fragment} subclass.
 */

public class ChooseProductCountDialogFragment extends BaseDialogFragment implements CartView{

    public static final String NEW_CART = "newCart";
    public static final int CART_UPDATE = 43;
    public static final String NEW_COUNT = "newCount";

    @Inject
    CartPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_product_count_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle bundle = getArguments();
        Result result = bundle.getParcelable(DETAIL_PRODUCT);

        TextView productName = (TextView) v.findViewById(R.id.choose_product_dialog_name);
        TextView productCost = (TextView) v.findViewById(R.id.choose_product_dialog_cost);
        EditText productCount = (EditText) v.findViewById(R.id.choose_product_dialog_count);
        productCount.setText("1");

//        define a filter to avoid too big count of items
        productCount.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "99")});
        TextView productTotalCost = (TextView) v.findViewById(R.id.choose_product_dialog_total_cost);
        productTotalCost.setText(Util.formatToDollar((float) result.getPriceInCents()));
        Button addToCart = (Button) v.findViewById(R.id.choose_product_dialog_add_to_cart_button);

        productName.setText(result.getName());
        productCost.setText(Util.formatToDollar((float) result.getPriceInCents()));

        productCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    productTotalCost.setText("0");
                }else {
                    productTotalCost.setText(Util.formatToDollar(Integer.parseInt(editable.toString()) *
                            result.getPriceInCents()));
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!productTotalCost.getText().toString().equals("0")) {
                    if (getTargetFragment() != null) {
                        Intent intent = new Intent();
                        intent.putExtra(NEW_CART, result);
                        intent.putExtra(NEW_COUNT, Integer.parseInt(productCount.getText().toString()));
                        getTargetFragment().onActivityResult(CART_UPDATE, Activity.RESULT_OK, intent);
                    } else {
                        mPresenter.addProductToCart(result, Integer.parseInt(productCount.getText().toString()), false);
                    }
                    dismiss();
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.init(this);
    }

    @Override
    public void setProductsFromCarts(List<Cart> carts) {}
}
