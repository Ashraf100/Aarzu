package com.aarzu.waqt.ramzan.xtra;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aarzu.waqt.R;

/**
 * Created by Drac Android on 3/20/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
   TextView sn, day, date, time;
    ItemClickListener itemClickListener;

    public MyViewHolder(View itemView) {
        super(itemView);
        sn = (TextView) itemView.findViewById(R.id.iftar_sn);
        day = (TextView) itemView.findViewById(R.id.iftar_day);
        date = (TextView) itemView.findViewById(R.id.iftar_date);
        time = (TextView) itemView.findViewById(R.id.iftar_time);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }
}
