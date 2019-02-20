package com.ojaexpress.merchant.ui.stores.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.Store;

import com.squareup.picasso.Picasso;

import java.util.List;


public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Store> stores;
    private final OnListItemSelected mListener;

    public StoreRecyclerViewAdapter(@NonNull OnListItemSelected listener) {
        mListener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Store store = stores.get(position);
        holder.mItem = store;
        holder.mNameView.setText(store.getName());
        holder.mAdminEmailView.setText(store.getAdmin().getEmail());

        Picasso.with(context)
                .load(store.getAvatar())
                .placeholder(R.drawable.storeicon)
                .error(R.drawable.storeicon)
                .into(holder.mStoreImageView);


        holder.mView.setOnClickListener(view -> mListener.onStoreSelected(holder.mItem));
    }

    @Override
    public int getItemCount() {
        if (stores == null) return 0;
        return stores.size();
    }

    void swapStores(List<Store> newStores) {
        if (stores == null) {
            stores = newStores;
            notifyDataSetChanged();
            return;
        }
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return stores.size();
            }

            @Override
            public int getNewListSize() {
                return newStores.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return stores.get(oldItemPosition).getId().equals(newStores.get(newItemPosition).getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Store oldStore = stores.get(oldItemPosition);
                Store newStore = newStores.get(newItemPosition);
                return oldStore.getId().equals(newStore.getId())
                        && oldStore.getName().equals(newStore.getName());
            }
        });
        stores = newStores;
        result.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mNameView;
        final TextView mAdminEmailView;
        final ImageView mStoreImageView;
        Store mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.store_name_text_view);
            mAdminEmailView = view.findViewById(R.id.store_admin_email_text_view);
            mStoreImageView = view.findViewById(R.id.store_image_view);
        }
    }

    public interface OnListItemSelected {
        void onStoreSelected(Store store);
    }
}
