package com.ojaexpress.merchant.ui.orders.detail;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.CartItem;
import com.squareup.picasso.Picasso;


import java.util.List;



public class OrderDetailRecyclerViewAdapter extends RecyclerView.Adapter<OrderDetailRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<CartItem> cartItems;



    public OrderDetailRecyclerViewAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_order_detail_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.mItem = cartItem;
        holder.mProductNameView.setText(cartItem.getProductName());
        holder.mPriceView.setText(String.format("%s%s", '$', cartItem.getProductPrice().toString()));
        holder.mCountView.setText(cartItem.getCount());

        Picasso.with(context)
                .load(cartItem.getProductImage())
                .placeholder(R.drawable.cart)
                .error(R.drawable.cart)
                .into(holder.mProductImageView);
    }

    @Override
    public int getItemCount() {
        if (cartItems == null) return 0;
        return cartItems.size();
    }


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mProductNameView;
        final TextView mPriceView;
        final TextView mCountView;

        final ImageView mProductImageView;

        CartItem mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mProductNameView = view.findViewById(R.id.productname);
            mPriceView = view.findViewById(R.id.price);
            mProductImageView = view.findViewById(R.id.pro);
          mCountView = view.findViewById(R.id.count);

        }
    }



}
