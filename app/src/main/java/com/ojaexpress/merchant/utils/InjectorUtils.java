package com.ojaexpress.merchant.utils;

import android.content.Context;

import com.ojaexpress.merchant.AppExecutors;
import com.ojaexpress.merchant.data.local.OjaExpressMerchantDatabase;
import com.ojaexpress.merchant.data.local.repositories.AuthRepository;
import com.ojaexpress.merchant.data.local.repositories.OrderRepository;
import com.ojaexpress.merchant.data.local.repositories.ProductRepository;
import com.ojaexpress.merchant.data.local.repositories.StoreRepository;
import com.ojaexpress.merchant.data.network.RetrofitFactory;
import com.ojaexpress.merchant.data.network.clients.AuthNetworkDataClient;
import com.ojaexpress.merchant.data.network.clients.OrderNetworkDataClient;
import com.ojaexpress.merchant.data.network.clients.ProductNetworkDataClient;
import com.ojaexpress.merchant.data.network.clients.StoreNetworkDataClient;
import com.ojaexpress.merchant.data.network.sources.AuthNetworkDataSource;
import com.ojaexpress.merchant.data.network.sources.OrderNetworkDataSource;
import com.ojaexpress.merchant.data.network.sources.ProductNetworkDataSource;
import com.ojaexpress.merchant.data.network.sources.StoreNetworkDataSource;
import com.ojaexpress.merchant.ui.auth.forgot_password.ForgotPasswordViewModelFactory;
import com.ojaexpress.merchant.ui.auth.login.LoginViewModelFactory;
import com.ojaexpress.merchant.ui.auth.register.SignUpViewModelFactory;
import com.ojaexpress.merchant.ui.orders.detail.OrderDetailViewModelFactory;
import com.ojaexpress.merchant.ui.orders.list.OrderListViewModelFactory;
import com.ojaexpress.merchant.ui.products.detail.ProductDetailViewModelFactory;
import com.ojaexpress.merchant.ui.products.list.ProductListViewModelFactory;
import com.ojaexpress.merchant.ui.stores.detail.StoreDetailViewModelFactory;
import com.ojaexpress.merchant.ui.stores.list.StoreListViewModelFactory;

/**
 * Created by funso on 23/05/2018.
 * <p>
 * Peace
 */

public class InjectorUtils {

    private static AuthNetworkDataSource provideAuthNetworkDataSource()
    {
        return AuthNetworkDataSource.getInstance(
                RetrofitFactory.getRetrofit().create(AuthNetworkDataClient.class)
        );
    }

    public static OrderNetworkDataSource provideOrderNetworkDataSource(Context context)
    {
        OrderNetworkDataClient client = RetrofitFactory
                .getRetrofit(provideAccessToken(context))
                .create(OrderNetworkDataClient.class);
        return OrderNetworkDataSource.getInstance(context, client);
    }

    public static ProductNetworkDataSource provideProductNetworkDataSource(Context context)
    {
        ProductNetworkDataClient client = RetrofitFactory
                .getRetrofit(provideAccessToken(context))
                .create(ProductNetworkDataClient.class);
        return ProductNetworkDataSource.getInstance(context, client);
    }

    public static StoreNetworkDataSource provideStoreNetworkDataSource(Context context)
    {
        StoreNetworkDataClient client = RetrofitFactory
                .getRetrofit(provideAccessToken(context))
                .create(StoreNetworkDataClient.class);
        return StoreNetworkDataSource.getInstance(context, client);
    }

    private static String provideAccessToken(Context context) {
        return CustomPreferenceManager.getInstance(context).getAccessToken();
    }

    private static AuthRepository provideAuthRepository(Context context)
    {
        AppExecutors executors = AppExecutors.getInstance();
        AuthNetworkDataSource networkDataSource = provideAuthNetworkDataSource();
        return AuthRepository.getInstance(networkDataSource, executors);
    }

    private static OrderRepository provideOrderRepository(Context context) {
        OjaExpressMerchantDatabase database = OjaExpressMerchantDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();
        OrderNetworkDataSource networkDataSource = provideOrderNetworkDataSource(context);
        return OrderRepository.getInstance(database.getOrderDao(), database.getAddressDao(), database.getCartItemDao(), networkDataSource, executors);
    }

    private static ProductRepository provideProductRepository(Context context) {
        OjaExpressMerchantDatabase database = OjaExpressMerchantDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();
        ProductNetworkDataSource networkDataSource = provideProductNetworkDataSource(context);
        return ProductRepository.getInstance(database.getProductDao(), networkDataSource, executors);
    }

    private static StoreRepository provideStoreRepository(Context context) {
        OjaExpressMerchantDatabase database = OjaExpressMerchantDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();
        StoreNetworkDataSource networkDataSource = provideStoreNetworkDataSource(context);
        return StoreRepository.getInstance(database.getStoreDao(), networkDataSource, executors);
    }


    public static LoginViewModelFactory provideLoginViewModelFactory(Context context)
    {
        AuthRepository repository = provideAuthRepository(context);
        return new LoginViewModelFactory(repository);
    }

    public static SignUpViewModelFactory provideSignUpViewModelFactory(Context context)
    {
        AuthRepository repository = provideAuthRepository(context);
        return new SignUpViewModelFactory(repository);
    }

    public static ForgotPasswordViewModelFactory provideForgotPasswordViewModelFactory(Context context)
    {
        AuthRepository repository = provideAuthRepository(context);
        return new ForgotPasswordViewModelFactory(repository);
    }

    public static OrderListViewModelFactory provideOrderListViewModelFactory(Context context) {
        return new OrderListViewModelFactory(provideOrderRepository(context));
    }

    public static OrderDetailViewModelFactory provideOrderDetailViewModelFactory(Context context, String orderId) {
        return new OrderDetailViewModelFactory(provideOrderRepository(context), orderId);
    }

    public static StoreListViewModelFactory provideStoreListViewModelFactory(Context context) {
        return new StoreListViewModelFactory(provideStoreRepository(context));
    }

    public static StoreDetailViewModelFactory provideStoreDetailViewModelFactory(Context context, String storeId) {
        return new StoreDetailViewModelFactory(provideStoreRepository(context), storeId);
    }

    public static ProductListViewModelFactory provideProductListViewModelFactory(Context context) {
        return new ProductListViewModelFactory(provideProductRepository(context));
    }

    public static ProductDetailViewModelFactory provideProductDetailViewModelFactory(Context context, String productId) {
        return new ProductDetailViewModelFactory(provideProductRepository(context), productId);
    }
}
