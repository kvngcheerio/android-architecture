package com.ojaexpress.merchant.ui.products.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.Product;
import com.ojaexpress.merchant.utils.ApiUtil;
import com.ojaexpress.merchant.utils.InjectorUtils;
import com.squareup.picasso.Picasso;

public class ProductDetailFragment extends Fragment {
    private static final String LOG_TAG = ProductDetailFragment.class.getSimpleName();
    private static final String PRODUCT_ID = "productId";
    private Context context;
    private String productId;
    private ProductDetailViewModel viewModel;

    TextView productName, price, stock, created, description, minstock, sold, clicks, views, searches;
    ImageView productImage;

    private OnFragmentInteractionListener mListener;

    private View mLayoutView;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(String productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productId = getArguments().getString(PRODUCT_ID);
        }
        ProductDetailViewModelFactory factory = InjectorUtils.provideProductDetailViewModelFactory(
                getContext(), productId
        );
        viewModel = ViewModelProviders.of(this, factory).get(ProductDetailViewModel.class);
        observeProduct();
        observeFetchStatus();
        observeFetchMsg();
    }

    private void bindProductDataToUI(Product product) {
        productName.setText(product.getName());
        price.setText(String.format("%s%s/%s", '$', product.getPrice().toString(), product.getUnitOfMeasure()));
        stock.setText(String.format("%s", product.getNumberInStock()));
        sold.setText(String.format("%s", product.getNumberSold()));
        minstock.setText(String.format("%s", product.getMinNumberForRestock()));
        created.setText(product.getCreatedAt().toString());
        description.setText(product.getDescription());
        clicks.setText(String.format("%s", product.getClicks()));
        views.setText(String.format("%s", product.getViews()));
        searches.setText(String.format("%s", product.getSearches()));
        Picasso.with(context)
                .load(product.getImageUrl())
                .placeholder(R.drawable.product)
                .error(R.drawable.product)
                .into(productImage);

    }

    private void observeProduct() {
        viewModel.getProduct().observe(this, fetchedProduct -> {
            if (fetchedProduct == null) return;
            bindProductDataToUI(fetchedProduct);
        });
    }

    private void observeFetchStatus(){
        viewModel.getFetchStatus().observe(this, status -> {
            Log.e(LOG_TAG, "Product details fetch status changed to => " + status);
            if (status == null) return;
            switch (status){
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
            Log.e(LOG_TAG, "Product details fetch Msg changed to => " + msg);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mLayoutView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        price = mLayoutView.findViewById(R.id.product_price_text_view);
        productName = mLayoutView.findViewById(R.id.product_name_text_view);
        productImage = mLayoutView.findViewById(R.id.product_image_view);
        stock = mLayoutView.findViewById(R.id.product_number_in_stock);
        sold = mLayoutView.findViewById(R.id.product_number_in_sold);
        minstock = mLayoutView.findViewById(R.id.product_number_in_restock);
        created = mLayoutView.findViewById(R.id.product_created);
        description = mLayoutView.findViewById(R.id.description);
       views = mLayoutView.findViewById(R.id.product_view);
       clicks = mLayoutView.findViewById(R.id.product_click);
       searches = mLayoutView.findViewById(R.id.product_search);
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
