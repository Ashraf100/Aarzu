package com.aarzu.waqt.ramzan.xtra;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.aarzu.waqt.R;
import com.aarzu.waqt.event.View.EventActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.design.widget.Snackbar.make;

public class Ramzan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ramzan_main);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Ramzan");
       setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("SHER"));
        tabLayout.addTab(tabLayout.newTab().setText("IFTAR"));
        tabLayout.addTab(tabLayout.newTab().setText("DUA"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/


    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

      /*  if (id == R.id.nav_namaz) {
//            Intent intent = new Intent(Ramzan.this, NamazActivity.class);
//            startActivity(intent);
            Snackbar snackbar =
                    make(drawerLayout, "Coming Soon!!", Snackbar.LENGTH_LONG);
            //  progressDialog = ProgressDialog.show(EventActivity.this, "Loading Data...", null);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();


            // Handle the camera action
        }
        *//*if (id == R.id.nav_compass) {
            Intent intent = new Intent(Ramzan.this, Compass.class);
            startActivity(intent);

        }*//*
        if (id == R.id.nav_mosque) {
            //Intent intent = new Intent(EventActivity.this, MosqueActivity.class);
            //startActivity(intent);
            Snackbar snackbar =
                    make(drawerLayout, "Coming Soon!!", Snackbar.LENGTH_LONG);
            //  progressDialog = ProgressDialog.show(EventActivity.this, "Loading Data...", null);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();


        }
        if (id == R.id.nav_calender) {
            Snackbar snackbar =
                    make(drawerLayout, "Coming Soon!!", Snackbar.LENGTH_LONG);
            //  progressDialog = ProgressDialog.show(EventActivity.this, "Loading Data...", null);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();


            //Intent intent = new Intent(EventActivity.this, Calender.class);
            //startActivity(intent);

        }*/
        if (id == R.id.nav_ramzan) {
            Intent intent = new Intent(Ramzan.this, Ramzan.class);
            startActivity(intent);
            finish();

        }
        if (id == R.id.nav_event) {
            Intent intent = new Intent(Ramzan.this, EventActivity.class);
            startActivity(intent);
             finish();
/*

        } else if (id == R.id.nav_tshirt) {
            Intent intent = new Intent(EventActivity.this, TshirtActivity.class);
            startActivity(intent);
*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
