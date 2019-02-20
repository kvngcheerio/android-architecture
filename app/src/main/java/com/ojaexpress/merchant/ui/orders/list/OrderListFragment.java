package com.ojaexpress.merchant.ui.orders.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.Order;
import com.ojaexpress.merchant.ui.orders.detail.OrderDetailFragment;
import com.ojaexpress.merchant.utils.ApiUtil;
import com.ojaexpress.merchant.utils.InjectorUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OrderListFragment extends Fragment implements
        OrderRecyclerViewAdapter.OnListItemSelectedListener {
    private boolean fabExpanded = false;
    private FloatingActionButton fab;
    private LinearLayout layoutFabDate;
    private LinearLayout layoutFabCost;
    private LinearLayout layoutFabStatus;

    private static final String LOG_TAG = OrderListFragment.class.getSimpleName();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private int mPosition = RecyclerView.NO_POSITION;

    private OnListFragmentInteractionListener mListener;
    private OrderRecyclerViewAdapter adapter;
    private OrderListViewModel viewModel;

    private RecyclerView recyclerView;
    private View mLayoutView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FrameLayout orderDetailContainerLayout;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OrderListFragment() {
    }

    public static OrderListFragment newInstance(int columnCount) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        OrderListViewModelFactory factory = InjectorUtils.provideOrderListViewModelFactory(getContext());
        viewModel = ViewModelProviders.of(this, factory).get(OrderListViewModel.class);
        observeOrders();
        observeFetchStatus();
        observeFetchMsg();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(R.layout.fragment_order_list, container, false);
        recyclerView = mLayoutView.findViewById(R.id.order_list_recycler_view);
        orderDetailContainerLayout = mLayoutView.findViewById(R.id.order_detail_container);
        fab = (FloatingActionButton) mLayoutView.findViewById(R.id.fab);
        layoutFabCost = (LinearLayout) mLayoutView.findViewById(R.id.layoutFabCost);
        layoutFabStatus = (LinearLayout) mLayoutView.findViewById(R.id.layoutFabStatus);
        layoutFabDate = (LinearLayout) mLayoutView.findViewById(R.id.layoutFabDate);

        setupSwipeRefreshLayout();
        setRecyclerViewLayoutManager(mLayoutView.getContext());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded) {
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });

        //Only main FAB is visible in the beginning
        closeSubMenusFab();


        layoutFabDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortListByDate(adapter.getOrders());
             //   adapter.notifyDataSetChanged();
            }
        });

        layoutFabStatus.setOnClickListener(v -> sortListByStatus(adapter.getOrders()));

        layoutFabCost.setOnClickListener(v -> sortListByCost(adapter.getOrders()));

        adapter = new OrderRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        return mLayoutView;
    }

    private void sortListByCost(List<Order> Orders) {

        Collections.sort(Orders, new OrderSortByCost());
    }


    private class OrderSortByCost implements java.util.Comparator<Order> {
        @Override
        public int compare(Order order1, Order order2) {
            String cost1, cost2;
            cost1 = order1.getGrandNetCost().toString().trim();
            cost2 = order2.getGrandNetCost().toString().trim();
            return cost1.compareTo(cost2);
        }
    }

    private void sortListByStatus(List<Order> Orders) {

        Collections.sort(Orders, new OrderSortByStatus());
    }


    private class OrderSortByStatus implements java.util.Comparator<Order> {
        @Override
        public int compare(Order order1, Order order2) {
            String status1, status2;
            status1 = order1.getState().trim();
            status2 = order2.getState().trim();
            return status1.compareTo(status2);
        }
    }


    private void sortListByDate(List<Order> Orders) {
        Collections.sort(Orders, new OrderSortByDate());
    }



    private class OrderSortByDate implements java.util.Comparator<Order> {
        @Override
        public int compare(Order order1, Order order2) {
            Date DateObject1 = (order1.getCreatedAt());
            Date DateObject2 = (order2.getCreatedAt());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(DateObject1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(DateObject2);

            int month1 = cal1.get(Calendar.MONTH);
            int month2 = cal2.get(Calendar.MONTH);

            if (month1 < month2)
                return -1;
            else if (month1 == month2)
                return cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH);

            else return 1;
        }
    }


    public static Date StringToDate(String theDateString) {
        Date returnDate = new Date();
        if (theDateString.contains("-")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            try {
                returnDate = dateFormat.parse(theDateString);
            } catch (ParseException e) {
                SimpleDateFormat altdateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    returnDate = altdateFormat.parse(theDateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                returnDate = dateFormat.parse(theDateString);
            } catch (ParseException e) {
                SimpleDateFormat altdateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    returnDate = altdateFormat.parse(theDateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return returnDate;
    }


    private void observeOrders() {
        viewModel.getOrders().observe(this, newOrders -> {
            if (newOrders == null) return;
            adapter.swapOrders(newOrders);
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
            recyclerView.smoothScrollToPosition(mPosition);
        });
    }

    private void observeFetchStatus() {
        viewModel.getFetchStatus().observe(this, status -> {
            Log.e(LOG_TAG, "Order list fetch status changed to => " + status);
            if (status == null) return;
            switch (status) {
                case ApiUtil.CALL_IN_PROGRESS:
                    showProgress(true);
                    break;
                case ApiUtil.CALL_FAILED:
                    showProgress(false);
                    break;
                case ApiUtil.CALL_SUCCESSFUL:
                    showProgress(false);
            }
        });
    }

    private void observeFetchMsg() {
        viewModel.getFetchMsg().observe(this, msg -> {
            Log.e(LOG_TAG, "Order list fetch Msg changed to => " + msg);
            if (msg == null) return;
            showMsg(msg);
        });
    }

    private void showMsg(String msg) {
        Snackbar.make(mLayoutView, msg, Snackbar.LENGTH_SHORT).show();
    }

    private void showProgress(boolean visible) {
        swipeRefreshLayout.setRefreshing(visible);
    }

    private void setRecyclerViewLayoutManager(Context context) {
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout = mLayoutView.findViewById(R.id.order_list_swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this::fetchLatestOrders);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
    }

    private void fetchLatestOrders() {
        viewModel.fetchLatestOrders();
    }

    private void closeSubMenusFab() {
        layoutFabStatus.setVisibility(View.INVISIBLE);
        layoutFabCost.setVisibility(View.INVISIBLE);
        layoutFabDate.setVisibility(View.INVISIBLE);
        fab.setImageResource(R.drawable.filter);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab() {
        layoutFabStatus.setVisibility(View.VISIBLE);
        layoutFabCost.setVisibility(View.VISIBLE);
        layoutFabDate.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fab.setImageResource(R.drawable.filter);
        fabExpanded = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onOrderSelected(Order order) {
        mListener.showOrderDetail(order, orderDetailContainerLayout);
    }

    public interface OnListFragmentInteractionListener {
        void showOrderDetail(Order order, @Nullable View detailContainerView);
    }
}
