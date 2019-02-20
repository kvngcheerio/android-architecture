package com.ojaexpress.merchant.ui.orders.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.CartItem;
import com.ojaexpress.merchant.data.local.models.Order;
import com.ojaexpress.merchant.data.local.models.OrderCartItem;
import com.ojaexpress.merchant.utils.ApiUtil;
import com.ojaexpress.merchant.utils.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailFragment extends Fragment {
    private static final String LOG_TAG = OrderDetailFragment.class.getSimpleName();
    private static final String ORDER_ID = "orderId";
    private Context context;
    private String orderId;
    private OnFragmentInteractionListener mListener;
    TextView price, customerName, customerAddress, customerMobile;
    ImageView avatar;
    RelativeTimeTextView time;
    private RecyclerView recyclerView;
    private OrderDetailViewModel viewModel;
    private int mColumnCount = 1;
    private int mPosition = RecyclerView.NO_POSITION;
    private OrderDetailRecyclerViewAdapter adapter;
    private List<CartItem> cartItems;


    private View mLayoutView;

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    public static OrderDetailFragment newInstance(String orderId) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putString(ORDER_ID, orderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            orderId = getArguments().getString(ORDER_ID);
        }
        OrderDetailViewModelFactory factory = InjectorUtils.provideOrderDetailViewModelFactory(
                this.getContext(), orderId);
        viewModel = ViewModelProviders.of(this, factory).get(OrderDetailViewModel.class);

//        observeOrder();
//        observeFetchStatus();
//        observeFetchMsg();




    }

    private void bindOrderDataToUI(Order order) {
        price.setText(String.format("%s%s", '$', order.getGrandNetCost().toString()));
        customerAddress.setText(order.getDeliveryAddressId());
        customerName.setText(String.format("%s %s", order.getUser().getFirstName(), order.getUser().getLastName()));
        customerMobile.setText(order.getUser().getPhoneNumber());
        time.setReferenceTime(order.getCreatedAt().getTime());




    }


//    private void observeOrder() {
//        viewModel.getOrderCart().observe(this, fetchedordercart -> {
//            if ( fetchedordercart== null) return;
//            bindOrderDataToUI(fetchedordercart);
//        });
//    }



    private void observeFetchStatus() {
        viewModel.getFetchStatus().observe(this, status -> {
            Log.e(LOG_TAG, "Order details fetch status changed to => " + status);
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
            Log.e(LOG_TAG, "Order details fetch Msg changed to => " + msg);
            if (msg == null) return;
            showMsg(msg);
        });
    }

    private void showMsg(String msg) {
        Snackbar.make(mLayoutView, msg, Snackbar.LENGTH_SHORT).show();
    }

    private void fetchOrderDetail() {
        viewModel.fetchOrderDetail();
    }

    private void showProgress(boolean visible) {
        // TODO: show/hide some sort of progress bar that indicates that a background process is happening
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mLayoutView = inflater.inflate(R.layout.fragment_order_detail, container, false);
        Context context = mLayoutView.getContext();
        price = (TextView) mLayoutView.findViewById(R.id.order_price_tv);
        customerName = (TextView) mLayoutView.findViewById(R.id.customername);
        customerAddress = (TextView) mLayoutView.findViewById(R.id.customeraddress);
        customerMobile = (TextView) mLayoutView.findViewById(R.id.mobile);
        avatar = (ImageView) mLayoutView.findViewById(R.id.order_store_avatar_image_view);
        time = (RelativeTimeTextView) mLayoutView.findViewById(R.id.order_datetime_tv);
        recyclerView = mLayoutView.findViewById(R.id.singleorder);
        adapter = new OrderDetailRecyclerViewAdapter(context,cartItems);

        setRecyclerViewLayoutManager(context);

        recyclerView.setAdapter(adapter);
        return mLayoutView;

    }

    private void setRecyclerViewLayoutManager(Context context) {
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
