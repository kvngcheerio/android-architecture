package com.ojaexpress.merchant.ui.products.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.Product;
import com.ojaexpress.merchant.utils.ApiUtil;
import com.ojaexpress.merchant.utils.InjectorUtils;

public class ProductListFragment extends Fragment
        implements SearchView.OnQueryTextListener,
        ProductRecyclerViewAdapter.OnListItemSelectedListener {

    private static final String LOG_TAG = ProductListFragment.class.getSimpleName();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private int mPosition = RecyclerView.NO_POSITION;

    private ProductRecyclerViewAdapter adapter;
    private ProductListViewModel viewModel;
    private OnListFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private View mLayoutView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FrameLayout productDetailContainerLayout;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductListFragment() {
    }

    public static ProductListFragment newInstance(int columnCount) {
        ProductListFragment fragment = new ProductListFragment();
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
        ProductListViewModelFactory factory = InjectorUtils.provideProductListViewModelFactory(getContext());
        viewModel = ViewModelProviders.of(this, factory).get(ProductListViewModel.class);
        observeProducts();
        observeFetchStatus();
        observeFetchMsg();
    }

    private void observeProducts() {
        viewModel.getProducts().observe(this, newOrders -> {
            if (newOrders == null) return;
            adapter.swapProducts(newOrders);
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
            recyclerView.smoothScrollToPosition(mPosition);
        });
    }

    private void observeFetchStatus() {
        viewModel.getFetchStatus().observe(this, status -> {
            Log.e(LOG_TAG, "Product list fetch status changed to => " + status);
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
            Log.e(LOG_TAG, "Product list fetch Msg changed to => " + msg);
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


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(R.layout.fragment_product_list, container, false);

        Context context = mLayoutView.getContext();
        productDetailContainerLayout = mLayoutView.findViewById(R.id.product_detail_container);
        recyclerView = mLayoutView.findViewById(R.id.product_list_recycler_view);
        adapter = new ProductRecyclerViewAdapter(this);

        setRecyclerViewLayoutManager(context);
        setupSwipeRefreshLayout();

        recyclerView.setAdapter(adapter);

//
//        SearchView searchView = mLayoutView.findViewById(R.id.search);
//        searchView.setOnQueryTextListener(this);

        return mLayoutView;
    }

    private void setRecyclerViewLayoutManager(Context context) {
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout = mLayoutView.findViewById(R.id.product_list_swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this::fetchLatestProducts);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
    }



    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }


    private void fetchLatestProducts() {
        viewModel.fetchLatestProducts();
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
    public void onProductSelected(Product product) {
        mListener.showProductDetail(product, productDetailContainerLayout);
    }

    public interface OnListFragmentInteractionListener {
        void showProductDetail(Product product, @Nullable View detailViewContainer);
    }

}
