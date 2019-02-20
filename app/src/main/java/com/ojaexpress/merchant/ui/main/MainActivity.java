package com.ojaexpress.merchant.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ojaexpress.merchant.R;
import com.ojaexpress.merchant.data.local.models.Order;
import com.ojaexpress.merchant.data.local.models.Product;
import com.ojaexpress.merchant.data.local.models.Store;
import com.ojaexpress.merchant.ui.auth.login.LoginActivity;
import com.ojaexpress.merchant.ui.orders.detail.OrderDetailFragment;
import com.ojaexpress.merchant.ui.orders.list.OrderListFragment;
import com.ojaexpress.merchant.ui.products.detail.ProductDetailFragment;
import com.ojaexpress.merchant.ui.products.list.ProductListFragment;
import com.ojaexpress.merchant.ui.settings.SettingsActivity;
import com.ojaexpress.merchant.ui.stores.detail.StoreDetailFragment;
import com.ojaexpress.merchant.ui.stores.list.StoreListFragment;
import com.ojaexpress.merchant.utils.CustomPreferenceManager;
import com.ojaexpress.merchant.utils.OjaExpressUtil;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OrderListFragment.OnListFragmentInteractionListener,
        StoreListFragment.OnListFragmentInteractionListener,
        ProductListFragment.OnListFragmentInteractionListener,
        OrderDetailFragment.OnFragmentInteractionListener,
        StoreDetailFragment.OnFragmentInteractionListener,
        ProductDetailFragment.OnFragmentInteractionListener{

    private TextView navEmailTextView;
    private TextView navUserNameTextView;
    private CircleImageView navUserImageView;

    private CustomPreferenceManager preferenceManager;

    private Toolbar toolbar;

    private static final String BACK_STACK_ROOT_TAG = "root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!CustomPreferenceManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        setContentView(R.layout.activity_main);


        initializeUIComponents();
        preferenceManager = CustomPreferenceManager.getInstance(this);
        populateUI();
        setToolbar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, OrderListFragment.newInstance(1))
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit();

            setHomeUpIndicator(true);
        }
    }

    private void initializeUIComponents() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navEmailTextView = navigationView.getHeaderView(0).findViewById(R.id.nav_email_text_view);
        navUserNameTextView = navigationView.getHeaderView(0).findViewById(R.id.nav_user_name_text_view);
        navUserImageView = navigationView.getHeaderView(0).findViewById(R.id.nav_image_view);
        toolbar = findViewById(R.id.toolbar);

    }

    private void populateUI() {

        final String email = preferenceManager.getUserEmail();
        final String firstName = preferenceManager.getUserFirstName();
        final String lastName = preferenceManager.getUserLastName();

        navEmailTextView.setText(email);

        navUserNameTextView.setText(OjaExpressUtil.getFullName(firstName, lastName));

        final String userImageUrl = preferenceManager.getUserImageUrl();
        Picasso.with(this)
                .load(userImageUrl)
                .placeholder(R.drawable.avatar)
                .into(navUserImageView);

    }

    public void setAppBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setAppBarTitle("OjaExpress");
    }

    private void setHomeUpIndicator(boolean flag) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            if (flag) {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            } else {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Fragment fragment = null;
        String title = null;

        if (id == R.id.nav_orders) {
            title = "Home";
            fragment = OrderListFragment.newInstance(1);
        } else if (id == R.id.nav_stores) {
            title = "Stores";
            fragment = StoreListFragment.newInstance(1);
        } else if (id == R.id.nav_products) {
            title = "Products";
            fragment = ProductListFragment.newInstance(1);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        changeFragment(fragment, title);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void changeFragment(Fragment fragment, String title) {
        if (fragment != null && !title.isEmpty()) {
            setHomeUpIndicator(true);
            setAppBarTitle(title);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit();
        }
    }


    private void changeFragment(Fragment fragment, @IdRes int containerViewId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }

    private void showDetailFragment(Fragment fragment, String title, @Nullable View detailContainerView) {
        if (detailContainerView == null) {
            changeFragment(fragment, title);
        } else {
            changeFragment(fragment, detailContainerView.getId());
        }
    }


    @Override
    public void showStoreDetail(Store item, @Nullable View detailContainerView) {
        Fragment fragment = StoreDetailFragment.newInstance(item.getId());
        showDetailFragment(fragment, "Store Details", detailContainerView);
    }



    @Override
    public void showProductDetail(Product product, @Nullable View detailContainerView) {
        Fragment fragment = ProductDetailFragment.newInstance(product.getId());
        showDetailFragment(fragment, "Product Details", detailContainerView);
    }

    @Override
    public void showOrderDetail(Order order, @Nullable View detailContainerView) {
        Fragment fragment = OrderDetailFragment.newInstance(order.getId());
        showDetailFragment(fragment, "Order Details", detailContainerView);
    }

//
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
