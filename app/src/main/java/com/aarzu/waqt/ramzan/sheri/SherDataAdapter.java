package com.aarzu.waqt.ramzan.sheri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aarzu.waqt.R;
import com.aarzu.waqt.model.AllTask;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;



  /* We are using the adapter similar to the one we used in our previous projects.
        We pass Androidversion model class as ArrayList in constructor.
        We obtain the Android version name, version number, API level from the AllTask object
        using the getter methods and set it to TextView widgets.*/


/**
 * Created by Drac Android on 2/10/2017.
 */


public class SherDataAdapter extends RecyclerView.Adapter<SherDataAdapter.ViewHolder> {
    private ArrayList<AllTask> sher;


    public SherDataAdapter(ArrayList<AllTask> sher) {
        this.sher = sher;
    }

    @Override
    public SherDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sher_card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SherDataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.sher_sn.setText(sher.get(i).getSsn());
        viewHolder.sher_day.setText(sher.get(i).getSday());
        viewHolder.sher_date.setText(sher.get(i).getSdate());
        viewHolder.sher_time.setText(sher.get(i).getStime());
    }

    @Override
    public int getItemCount() {
        return sher.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sher_sn)
        TextView sher_sn;
        @BindView(R.id.sher_day)
        TextView sher_day;
        @BindView(R.id.sher_date)
        TextView sher_date;
        @BindView(R.id.sher_time)
        TextView sher_time;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }


}