package com.aarzu.waqt.event.Implementor;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

/**
 * Created by burni on 3/1/2017.
 */

public class EventImplementor extends AppCompatActivity implements EventPresenter {

    private ArrayList<AllTask> eventData;
    private EventView view;
    private ProgressDialog progressDialog;


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
              //  progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("EventError", t.getMessage());
            }
        });


    }

}

