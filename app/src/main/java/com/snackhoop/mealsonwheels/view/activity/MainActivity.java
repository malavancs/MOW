package com.snackhoop.mealsonwheels.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.snackhoop.mealsonwheels.R;
import com.snackhoop.mealsonwheels.presenter.MainActivityPresenter;
import com.snackhoop.mealsonwheels.presenter.ipresenter.IMainActivityPresenter;
import com.snackhoop.mealsonwheels.view.fragment.JourneyBottomSheet;
import com.snackhoop.mealsonwheels.view.iview.IMainActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMainActivityPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, IMainActivityView {

    @BindView(R.id.restuarent_near_me)
    Button restuarentNearMe;
    @BindView(R.id.planning_trip)
    Button planningTrip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @NonNull
    @Override
    IMainActivityPresenter bindView(@Nullable Bundle savedInstanceState) {

        return new MainActivityPresenter(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.restuarent_near_me, R.id.planning_trip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.restuarent_near_me:
                JourneyBottomSheet journeyBottomSheet = new JourneyBottomSheet();
                journeyBottomSheet.show(getSupportFragmentManager(),"details");
                break;
            case R.id.planning_trip:
                Toast.makeText(this, "Planning trip", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
