package com.ojaexpress.merchant.ui.products.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.Product;
import com.ojaexpress.merchant.ui.products.list.ProductListFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Product> products;
    private final OnListItemSelectedListener mListener;

    public ProductRecyclerViewAdapter(OnListItemSelectedListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.mItem = product;
        holder.mNameView.setText(product.getName());
        holder.mPriceView.setText(String.format("%s%s/%s", '$', product.getPrice().toString(), product.getUnitOfMeasure()));
        holder.mStockView.setText(String.format("%s", product.getNumberInStock()));
        holder.mCategoryView.setText(product.getDescription());
        Picasso.with(context)
                .load(product.getImageUrl())
                .placeholder(R.drawable.oja_logo)
                .error(R.drawable.oja_logo)
                .into(holder.mImageView);

        holder.mView.setOnClickListener(view -> {
            if (null != mListener) {
                mListener.onProductSelected(holder.mItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (products == null) return 0;
        return products.size();
     //   Log.e("msg" + products.size());
    }


    void swapProducts(List<Product> newProducts) {
        if (products == null) {
            products = newProducts;
            return;
        }

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return products.size();
            }

            @Override
            public int getNewListSize() {
                return newProducts.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return products.get(oldItemPosition).getId().equals(newProducts.get(newItemPosition).getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Product oldProduct = products.get(oldItemPosition);
                Product newProduct = newProducts.get(newItemPosition);
                return oldProduct.getId().equals(newProduct.getId())
                        && oldProduct.getName().equals(newProduct.getName());
            }
        });

        products = newProducts;
        result.dispatchUpdatesTo(this);
    }

    // Filter Class
    public void filter(String charText) {
        if (charText.length() == 0) {
            return;
        }
        charText = charText.toLowerCase(Locale.getDefault());
        products.clear();

        for (Product product : products) {
            if (product.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                products.add(product);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mNameView;
        final TextView mPriceView;
        final TextView mStockView;
        final TextView mCategoryView;
        final CircleImageView mImageView;
        Product mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.product_name_text_view);
            mPriceView = view.findViewById(R.id.product_price_text_view);
            mStockView = view.findViewById(R.id.product_number_in_stock);
            mCategoryView = view.findViewById(R.id.product_category);
            mImageView = view.findViewById(R.id.product_image_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }

    public interface OnListItemSelectedListener {
        void onProductSelected(Product product);
    }

}
