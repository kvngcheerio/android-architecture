package com.ojaexpress.merchant.ui.stores.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.Store;
import com.ojaexpress.merchant.utils.ApiUtil;
import com.ojaexpress.merchant.utils.InjectorUtils;
import com.squareup.picasso.Picasso;

public class StoreDetailFragment extends Fragment {
    private static final String LOG_TAG = StoreDetailFragment.class.getSimpleName();
    private static final String STORE_ID = "storeId";

    TextView adminEmail, storeName;
    ImageView avatar;
    private String storeId;
    private Context context;
    private StoreDetailViewModel viewModel;
    private OnFragmentInteractionListener mListener;

    private View mLayoutView;

    public StoreDetailFragment() {
        // Required empty public constructor
    }

    public static StoreDetailFragment newInstance(String storeId) {
        StoreDetailFragment fragment = new StoreDetailFragment();
        Bundle args = new Bundle();
        args.putString(STORE_ID, storeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            storeId = getArguments().getString(STORE_ID);
        }
        StoreDetailViewModelFactory factory = InjectorUtils.provideStoreDetailViewModelFactory(getContext(), storeId);
        viewModel = ViewModelProviders.of(this, factory).get(StoreDetailViewModel.class);
        observeStore();
        observeFetchStatus();
        observeFetchMsg();
    }

    private void bindStoreToUI(Store store) {
        adminEmail.setText(store.getAdmin().getEmail());
        storeName.setText(store.getName());

        Picasso.with(context)
                .load(store.getAvatar())
                .placeholder(R.drawable.oja_logo)
                .error(R.drawable.oja_logo)
                .into(avatar);
    }

    private void observeStore() {
        viewModel.getStore().observe(this, fetchedStore -> {
            if (fetchedStore == null) return;
            bindStoreToUI(fetchedStore);
        });
    }

    private void observeFetchStatus() {
        viewModel.getFetchStatus().observe(this, status -> {
            Log.e(LOG_TAG, "Store details fetch status changed to => " + status);
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
            Log.e(LOG_TAG, "Store details fetch Msg changed to => " + msg);
            if (msg == null) return;
            showMsg(msg);
        });
    }

    private void showMsg(String msg) {
        Snackbar.make(mLayoutView, msg, Snackbar.LENGTH_SHORT).show();
    }

    private void showProgress(boolean visible) {
        // TODO: show/hide some sort of progress bar that indicates that a background process is happening
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mLayoutView = inflater.inflate(R.layout.fragment_store_detail, container, false);
        storeName = (TextView) mLayoutView.findViewById(R.id.store_name_text_view);
        adminEmail = (TextView) mLayoutView.findViewById(R.id.store_admin_email_text_view);
        avatar = (ImageView) mLayoutView.findViewById(R.id.order_store_avatar_image_view);

        return mLayoutView;
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
