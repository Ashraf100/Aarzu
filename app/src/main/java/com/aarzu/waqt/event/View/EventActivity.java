package com.aarzu.waqt.event.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aarzu.waqt.R;
import com.aarzu.waqt.event.Implementor.EventDataAdapter;
import com.aarzu.waqt.event.Implementor.EventImplementor;
import com.aarzu.waqt.event.Presenter.EventPresenter;
import com.aarzu.waqt.model.AllTask;
import com.aarzu.waqt.ramzan.xtra.Ramzan;
import com.aarzu.waqt.splash.ConnectionDetector;

import com.stacktips.view.CalendarListener;
import com.stacktips.view.CustomCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.design.widget.Snackbar.make;


/**
 * Created by burni on 3/1/2017.
 */

public class EventActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener, EventView {

    private ShareActionProvider mShareActionProvider;

    @BindView(R.id.card_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    CustomCalendarView customCalendarView;

    private EventDataAdapter eventDataAdapter;
    private ConnectionDetector connectionDetector;
    private ProgressDialog progressDialog;
    private Snackbar snackbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //EventActivity.this.getSupportActionBar().setTitle("Upcoming Event");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Upcoming Event");
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
       /* if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        // getSupportActionBar().setIcon(R.drawable.home);
        //  DrawerLayout drawerLayout =

        connectionDetector = new ConnectionDetector(this);
        intView();
        //  snackbar.dismiss();

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Initialize CustomCalendarView from back
        customCalendarView = (CustomCalendarView) findViewById(R.id.calendarView);

//Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

//Show Monday as first date of week
        customCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);

//Show/hide overflow days of a month
        customCalendarView.setShowOverflowDate(false);

//call refreshCalendar to update calendar the view
        customCalendarView.refreshCalendar(currentCalendar);

//Handling custom calendar events
        customCalendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Toast.makeText(EventActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(EventActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        //Setting custom font
       /* final Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Arch_Rival_Bold.ttf");
        if (null != typeface) {
            customCalendarView.setCustomTypeface(typeface);
            customCalendarView.refreshCalendar(currentCalendar);
        }*/

    }

    public void intView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        if (connectionDetector.isConnected()) {
            Snackbar snackbar =
                    make(drawerLayout, "Loading Data!!", Snackbar.LENGTH_LONG);
            //  progressDialog = ProgressDialog.show(EventActivity.this, "Loading Data...", null);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();

            EventPresenter eventPresenter = new EventImplementor(this);
            eventPresenter.loadEventJson();
            // snackbar.dismiss();
            // progressDialog.dismiss();
        } else {

            Snackbar snackbar =
                    make(drawerLayout, "No Internet Connection!!", Snackbar.LENGTH_INDEFINITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.RED);
            TextView onSnackBar = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            onSnackBar.setTextColor(Color.WHITE);
            snackbar.show();

        }

    }

    @Override
    public void showEventRecyclerView(ArrayList<AllTask> allTasks) {
        eventDataAdapter = new EventDataAdapter(allTasks);
        recyclerView.setAdapter(eventDataAdapter);

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent i = new Intent(this, Ramzan.class);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        /*if (id == R.id.nav_namaz) {
            //Intent intent = new Intent(EventActivity.this, NamazActivity.class);
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


            // Handle the camera action
        } else if (id == R.id.nav_mosque) {
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


        } else if (id == R.id.nav_calender) {
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

        } else*/ if (id == R.id.nav_ramzan) {
            Intent intent = new Intent(EventActivity.this, Ramzan.class);
            startActivity(intent);


        } else if (id == R.id.nav_event) {
            Intent intent = new Intent(EventActivity.this, EventActivity.class);
            startActivity(intent);
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
