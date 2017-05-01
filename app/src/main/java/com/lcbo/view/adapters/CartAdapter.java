package com.lcbo.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lcbo.R;
import com.lcbo.model.db.Cart;
import com.lcbo.util.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Cart> mCarts;
    private Context mContext;
    private CartListener mCartListener;

    public CartAdapter (List<Cart> carts, Context context, CartListener cartListener) {
        this.mCarts = carts;
        mContext = context;
        mCartListener = cartListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);

        StoresAdapter.ViewHolder vh = new StoresAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;

        ImageView cartImg = (ImageView) view.findViewById(R.id.cart_img);
        Picasso.with(mContext).load(mCarts.get(position).getProductImg()).into(cartImg);

        TextView cartName = (TextView) view.findViewById(R.id.cart_name);
        TextView cartMl = (TextView) view.findViewById(R.id.cart_ml);
        TextView cartCount = (TextView) view.findViewById(R.id.cart_count);
        TextView cartTotalCost = (TextView) view.findViewById(R.id.cart_total_cost);
        ImageView deleteCartImg = (ImageView) view.findViewById(R.id.cart_delete_image);

        cartName.setText(mCarts.get(position).getProductName());
        cartMl.setText(mCarts.get(position).getProductMl());
        cartCount.setText(Integer.toString(mCarts.get(position).getCount()));
        cartTotalCost.setText(Util.formatToDollar(mCarts.get(position).getCount() * mCarts.get(position).getCost()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCartListener.onChangeCount(mCarts.get(position));

            }
        });

        deleteCartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCartListener.onDeleteCart((mCarts.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCarts.size();
    }

    public void setmCarts(List<Cart> mCarts) {
        this.mCarts = mCarts;
    }

    public interface CartListener{
        void onChangeCount(Cart cart);
        void onDeleteCart(Cart cart);
    }
}
