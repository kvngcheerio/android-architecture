package com.ojaexpress.merchant.ui.orders.list;

import android.content.Context;
import android.database.Cursor;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.Order;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder> {
    private Cursor cursor;
    private Context context;
    private List<Order> orders;
    private final OnListItemSelectedListener mListener;

    public OrderRecyclerViewAdapter(@NonNull OnListItemSelectedListener listener) {
        mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.mItem = order;
        holder.mOrderIdView.setText(order.getId().toUpperCase());
        holder.mOrderNetCostView.setText(String.format("%s%s", '$', order.getGrandNetCost().toString()));
        holder.mOrderTimeView.setReferenceTime(order.getCreatedAt().getTime());
        holder.mOrderStatusView.setText(String.format("%s", order.getState()));

        Picasso.with(context)
                .load(order.getStoreId())
                .placeholder(R.drawable.usericon)
                .error(R.drawable.usericon)
                .into(holder.mOrderStoreImageView);

        holder.mView.setOnClickListener(view -> mListener.onOrderSelected(holder.mItem));


    }

    @Override
    public int getItemCount() {
        if (orders == null) return 0;
        return orders.size();
    }

    void swapOrders(final List<Order> newOrders) {
        if (orders == null) {
            orders = newOrders;
            notifyDataSetChanged();
            return;
        }
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return orders.size();
            }

            @Override
            public int getNewListSize() {
                return newOrders.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return orders.get(oldItemPosition).getId().equals(newOrders.get(newItemPosition).getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Order oldOrder = orders.get(oldItemPosition);
                Order newOrder = newOrders.get(newItemPosition);
                return oldOrder.getId().equals(newOrder.getId())
                        && oldOrder.getUserId().equals(newOrder.getUserId());
            }
        });
        orders = newOrders;
        result.dispatchUpdatesTo(this);
    }

    public List<Order> getOrders() {
        return orders;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mOrderIdView;
        final TextView mOrderNetCostView;
        final TextView mOrderStatusView;
        final RelativeTimeTextView mOrderTimeView;
        final CircleImageView mOrderStoreImageView;
        private SparseBooleanArray selectedItem = new SparseBooleanArray();


        Order mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mOrderIdView = view.findViewById(R.id.order_id_tv);
            mOrderNetCostView = view.findViewById(R.id.order_net_cost_tv);
            mOrderStoreImageView = view.findViewById(R.id.order_store_avatar_image_view);
            mOrderTimeView = view.findViewById(R.id.order_datetime_tv);
            mOrderStatusView = view.findViewById(R.id.order_status);

        }
    }

    public interface OnListItemSelectedListener {
        void onOrderSelected(Order order);
    }

//    private class OrderListCallback extends SortedList.Callback<Order> {
//
//        @Override
//        public int compare(Order p1, Order p2) {
//            String[] order1 = p1.getId().split("\n");
//            String[] order2 = p2.getId().split("\n");
//            int diff = Integer.parseInt(order1[0]) - Integer.parseInt(order2[0]);
//            return (diff == 0) ? p1.getCreatedAt().compareTo(p2.getCreatedAt()) : diff;
//        }
//
//        @Override
//        public void onInserted(int position, int count) {
//            notifyItemInserted(position);
//        }
//
//        @Override
//        public void onRemoved(int position, int count) {
//            notifyItemRemoved(position);
//        }
//
//        @Override
//        public void onMoved(int fromPosition, int toPosition) {
//        }
//
//        @Override
//        public void onChanged(int position, int count) {
//        }
//
//        @Override
//        public boolean areContentsTheSame(Order oldItem, Order newItem) {
//            return false;
//        }
//
//        @Override
//        public boolean areItemsTheSame(Order item1, Order item2) {
//            return false;
//        }
//
//    }

}
