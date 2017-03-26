package com.aarzu.waqt.ramzan.iftar;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.design.widget.Snackbar.make;
import static com.aarzu.waqt.R.id.adView;
//import static com.aarzu.waqt.R.id.iftaradView;

/**
 * Created by Drac Android on 3/6/2017.
 */
public class IftarTabFragment extends Fragment {
    private IftarDataAdapter adapter;
    private ConnectionDetector connectionDetector;
    //  private IftarView iftarView;
    private ArrayList<AllTask> iftarData;
    ProgressDialog dialog;
    private RelativeLayout relativeLayout;
    InterstitialAd interstitialAd;
    private AdView adView;

    //@BindView(R.id.iftar_card_recycler_view)
    //RecyclerView recyclerView;
//    @BindView(R.id.drawer_layout)
//    TextView iday;
    ProgressDialog progressDialog;
    //  Realm realm;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.iftartab_fragment, container, false);
        connectionDetector = new ConnectionDetector(getActivity());
        relativeLayout = (RelativeLayout) v.findViewById(R.id.iftar_relative);
      //  ButterKnife.bind(this,v);
       // Typeface typeface = Typeface.createFromAsset()
        //Realm.init(this.getActivity());
        //realm = Realm.getDefaultInstance();
        //intView();
       /* RecyclerView rv = (RecyclerView) v.findViewById(R.id.iftard_card_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);*/
        // loadIftarJson();
        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getString(R.string.aarzu_full_screen));

        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);


        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                displayInterstitial();
            }
        });


        return v;
    }
    private void displayInterstitial() {
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }

    @Override
    public void onPause() {
        if (adView != null){
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (adView != null){
            adView.resume();
        }
        super.onResume();

    }

    @Override
    public void onDestroy() {
        if (adView != null){
            adView.destroy();
        }
        super.onDestroy();
    }
    /*private void saveData() {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
               // AllTask data = bgRealm.createObject(AllTask.class);
                data.setSdate(data.getSdate());
                data.setSday(data.getSday());
                data.setStime(data.getStime());


//                user.setName("John");
//                user.setEmail("john@corporation.com");
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d("Transaction", ">>>>>Data Stored Completed<<<<<<<<<<");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("database", error.getMessage());

            }
        });
    }
*/

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        if (connectionDetector.isConnected()) {
            // Toast.makeText(IftarTabFragment.this.getActivity(), "Connected", Toast.LENGTH_SHORT).show();

            Snackbar snackbar =
                    make(relativeLayout, "Loading Data!!", Snackbar.LENGTH_LONG);
            //  progressDialog = ProgressDialog.show(EventActivity.this, "Loading Data...", null);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            progressDialog = ProgressDialog.show(IftarTabFragment.this.getActivity(), "Loading Data...", null);


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
                    //saveData();

                    RecyclerView rv = (RecyclerView) v.findViewById(R.id.iftard_card_recycler_view);
                    rv.setHasFixedSize(true);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(llm);
                    adapter = new IftarDataAdapter(iftarData);
                    rv.setAdapter(adapter);
                    progressDialog.dismiss();
                    // dialog.dismiss();
                    //Calls method form IftarView interface class.
                    //   iftarView.showRecyclerView(iftarData);
                    // dialog.dismiss();
                }

                @Override
                public void onFailure(Call<JSONResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });


        } else {
            //  Toast.makeText(IftarTabFragment.this.getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();

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

    /*

    private void showLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(IftarTabFragment.this.getActivity());
        builder.setTitle(getString(R.string.dialog_title));
        builder.setMessage(getString(R.string.dialog_message));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
*/


    public void intView() {
//        recyclerView.setHasFixedSize(true);
        //  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);

        if (connectionDetector.isConnected()) {
            /*Snackbar snackbar =
                    make(drawerLayout, "Loading Data!!", Snackbar.LENGTH_LONG);
            //  progressDialog = ProgressDialog.show(EventActivity.this, "Loading Data...", null);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
 */          // Toast.makeText(this, "Connected!!!", Toast.LENGTH_SHORT).show();
            //  MosquePresenter mosquePresenter = new MosqueImplementor(this);
            // dialog = ProgressDialog.show(this, "Loading Data...", null);
            //mosquePresenter.loadJson();
            //  dialog.dismiss();
        } else {
            // Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
          /*  Snackbar snackbar =
                    make(drawerLayout, "No Internet Connection!!", Snackbar.LENGTH_INDEFINITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.RED);
            TextView onSnackBar = (TextView)
                    snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            onSnackBar.setTextColor(Color.WHITE);
            snackbar.show();*/
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
                // recyclerView.setAdapter(adapter);
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
/**
 * @param allTasks
 */
  /*  @Override
    public View showRecyclerView(ArrayList<AllTask> allTasks) {
        adapter = new MosqueDataAdapter(allTasks);
        recyclerView.setAdapter(adapter);
        return v ;
    }*/
//}