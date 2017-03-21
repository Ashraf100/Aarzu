package com.aarzu.waqt.event.Implementor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aarzu.waqt.R;
import com.aarzu.waqt.model.AllTask;


import java.util.ArrayList;

/**
 * Created by Drac Android on 2/10/2017.
 */

       /* We are using the adapter similar to the one we used in our previous projects.
        We pass Androidversion model class as ArrayList in constructor.
        We obtain the Android version name, version number, API level from the AllTask object
        using the getter methods and set it to TextView widgets.*/

public class EventDataAdapter extends RecyclerView.Adapter<EventDataAdapter.ViewHolder> {
    private ArrayList<AllTask> event;
    // @BindView(R.id.tv_name) TextView tv_name;

    public EventDataAdapter(ArrayList<AllTask> event) {
        this.event = event;
    }

    @Override
    public EventDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventDataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.event_title.setText(event.get(i).getTitle());
        viewHolder.event_date.setText(event.get(i).getDate());
        viewHolder.event_venue.setText(event.get(i).getVenue());
        viewHolder.event_time.setText(event.get(i).getTime());
        viewHolder.event_description.setText(event.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return event.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView event_title, event_date, event_time, event_venue, event_description;

        public ViewHolder(View view) {
            super(view);

            event_title = (TextView) view.findViewById(R.id.event_title);
            event_date = (TextView) view.findViewById(R.id.event_date);
            event_time = (TextView) view.findViewById(R.id.event_time);
            event_venue = (TextView) view.findViewById(R.id.event_venue);
            event_description = (TextView) view.findViewById(R.id.event_description);


        }
    }

}