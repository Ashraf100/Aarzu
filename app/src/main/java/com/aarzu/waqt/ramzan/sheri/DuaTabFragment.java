package com.aarzu.waqt.ramzan.sheri;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aarzu.waqt.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static com.aarzu.waqt.R.id.dua_adView;
//import static com.aarzu.waqt.R.id.iftaradView;

/**
 * Created by burni on 3/9/2017.
 */

public class DuaTabFragment extends android.support.v4.app.Fragment {

    View v;
    private AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.duatab_fragment, container, false);
        adView = (AdView) v.findViewById(dua_adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

        return v;
    }
}
