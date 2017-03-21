package com.aarzu.waqt.ramzan.sheri;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.aarzu.waqt.R;
import com.aarzu.waqt.model.AllTask;
import com.aarzu.waqt.model.JSONResponse;
import com.aarzu.waqt.model.RequestInterface;
import com.aarzu.waqt.splash.ConnectionDetector;

import java.util.ArrayList;
import java.util.Arrays;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.design.widget.Snackbar.make;

/**
 * Created by Drac Android on 3/6/2017.
 */
public class SherTabFragment extends Fragment {
     SherDataAdapter sherDataAdapter;
    private ArrayList<AllTask> sherData;
    private ConnectionDetector connectionDetector;
    private RelativeLayout relativeLayout;
    //ProgressDialog dialog;
   /* @BindView(R.id.mosque_card_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;*/
   /* @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;*/
    ProgressDialog progressDialog;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.shertab_fragment, container, false);
        //ButterKnife.bind(this);

        connectionDetector = new ConnectionDetector(getActivity());
         relativeLayout = (RelativeLayout) v.findViewById(R.id.relative);
        //intView();
       /* RecyclerView rv = (RecyclerView) v.findViewById(R.id.iftard_card_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);*/
        // loadIftarJson();




        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (connectionDetector.isConnected()) {
           // Toast.makeText(SherTabFragment.this.getActivity(), "Connected", Toast.LENGTH_SHORT).show();
            Snackbar snackbar =
                    make(relativeLayout, "Loading Data!!", Snackbar.LENGTH_LONG);
            //  progressDialog = ProgressDialog.show(EventActivity.this, "Loading Data...", null);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();

            progressDialog = ProgressDialog.show(SherTabFragment.this.getActivity(), "Loading Data...", null);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://waqt.000webhostapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RequestInterface request = retrofit.create(RequestInterface.class);
            Call<JSONResponse> responseCall = request.getSherJSON();
            responseCall.enqueue(new Callback<JSONResponse>() {
                @Override
                public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                    JSONResponse jsonResponse = response.body();
                    sherData = new ArrayList<>(Arrays.asList(jsonResponse.getSher()));
                    RecyclerView rv = (RecyclerView) v.findViewById(R.id.sherd_card_recycler_view);
                    rv.setHasFixedSize(true);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(llm);
                    sherDataAdapter = new SherDataAdapter(sherData);
                    rv.setAdapter(sherDataAdapter);
                    progressDialog.dismiss();
                    //sherDataAdapter = new IftarDataAdapter(sherData);
                    // rv.setAdapter(sherData);
                    //Calls method form IftarView interface class.
                    //   iftarView.showRecyclerView(iftarData);
                    // dialog.dismiss();
                }

                @Override
                public void onFailure(Call<JSONResponse> call, Throwable t) {
                    Log.d("MosqueError", t.getMessage());
                }
            });

        }
        else {
          //  Toast.makeText(SherTabFragment.this.getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();

            Snackbar snackbar =
                    make(relativeLayout, "No Internet Connection!!", Snackbar.LENGTH_INDEFINITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.RED);
            TextView onSnackBar = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            onSnackBar.setTextColor(Color.WHITE);
            snackbar.show();

        }


    }

    /* public void intView() {
//        recyclerView.setHasFixedSize(true);
        //  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);

        if (connectionDetector.isConnected()) {
            *//*Snackbar snackbar =
                    make(drawerLayout, "Loading Data!!", Snackbar.LENGTH_LONG);
            //  progressDialog = ProgressDialog.show(EventActivity.this, "Loading Data...", null);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
 *//*          // Toast.makeText(this, "Connected!!!", Toast.LENGTH_SHORT).show();
            //  MosquePresenter mosquePresenter = new MosqueImplementor(this);
            // dialog = ProgressDialog.show(this, "Loading Data...", null);
            //mosquePresenter.loadJson();
            //  dialog.dismiss();
        } else {
            // Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
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
    public void loadIftarJson() {
        //dialog = ProgressDialog.show(this, "Loading Data...", null);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://waqt.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> responseCall = request.getIftarJSON();
        responseCall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                iftarData = new ArrayList<>(Arrays.asList(jsonResponse.getIftar()));
                //  adapter = new MosqueDataAdapter(iftarData);
                recyclerView.setAdapter(adapter);
                //Calls method form IftarView interface class.
                //   iftarView.showRecyclerView(iftarData);
                // dialog.dismiss();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("MosqueError", t.getMessage());
            }
        });
    }
}
*//**
 *
 * @param allTasks
 *//*
  *//*  @Override
    public View showRecyclerView(ArrayList<AllTask> allTasks) {
        adapter = new MosqueDataAdapter(allTasks);
        recyclerView.setAdapter(adapter);
        return v ;
    }*//*
//}
    }*/
}