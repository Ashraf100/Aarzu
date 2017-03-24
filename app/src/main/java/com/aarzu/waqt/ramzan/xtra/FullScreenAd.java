package com.aarzu.waqt.ramzan.xtra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aarzu.waqt.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Drac Android on 3/24/2017.
 */

public class FullScreenAd extends AppCompatActivity {
    private String TAG = FullScreenAd.class.getSimpleName();
    InterstitialAd interstitialAd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.full_ad_layout);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.banner_home_footer));

        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showInterstitial();
            }

        });

    }

    private void showInterstitial() {
    if (interstitialAd.isLoaded()){
        interstitialAd.show();
    }

    }

}
