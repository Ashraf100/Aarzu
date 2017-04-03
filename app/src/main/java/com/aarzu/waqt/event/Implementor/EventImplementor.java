package com.aarzu.waqt.event.Implementor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.aarzu.waqt.event.Presenter.EventPresenter;
import com.aarzu.waqt.event.View.EventView;
import com.aarzu.waqt.model.AllTask;
import com.aarzu.waqt.model.JSONResponse;
import com.aarzu.waqt.model.RequestInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aarzu.waqt.event.View.EventActivity.MyPREFERENCES;
import static com.aarzu.waqt.event.View.EventActivity.Time;

/**
 * Created by burni on 3/1/2017.
 */

public class EventImplementor extends AppCompatActivity implements EventPresenter {

    private ArrayList<AllTask> eventData;
    private EventView view;
    private ProgressDialog progressDialog;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Title = "title";
    public static final String Date = "date";
    public static final String Address = "address";
    public static final String Day = "day";
    public static final String Venue = "venue";
    public static final String Description = "desc";

    SharedPreferences sharedpreferences;

    public EventImplementor(EventView view) {
        this.view = view;

    }


    @Override
    public void loadEventJson() {
       // progressDialog = ProgressDialog.show(EventActivity., "Loading Data...", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://waqt.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = requestInterface.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

                eventData = new ArrayList<>(Arrays.asList(jsonResponse.getEvent()));
                view.showEventRecyclerView(eventData);
//               AllTask allTask = new AllTask();
//
//
//               String tl  = allTask.getTitle().toString();
//               String tm  = allTask.getTime().toString();
//               String dt  = allTask.getDate().toString();
//               String ad  = allTask.getAddress().toString();
//               String v   = allTask.getVenue().toString();
//               String de   = allTask.getDescription().toString();
//               // sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//               // SharedPreferences.Editor editor = sharedpreferences.edit();
//                SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
//                editor.putString(Title,tl);
//                editor.putString(Time,tm);
//                editor.putString(Date,dt);
//                editor.putString(Address,ad);
//                editor.putString(Venue,v);
//                editor.putString(Description,de);
//                editor.commit();
//                Toast.makeText(EventImplementor.this,"Thanks", Toast.LENGTH_LONG).show();
                view.showEventRecyclerView(eventData);

              //  progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("EventError", t.getMessage());
            }
        });


    }

}



