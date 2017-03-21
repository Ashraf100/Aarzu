package com.aarzu.waqt.ramzan.sheri;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aarzu.waqt.R;

/**
 * Created by burni on 3/9/2017.
 */

public class DuaTabFragment extends android.support.v4.app.Fragment {

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.duatab_fragment, container, false);
    return v;
    }
}
