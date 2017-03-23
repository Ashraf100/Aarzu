package com.aarzu.waqt.ramzan.iftar;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.aarzu.waqt.R;
import com.aarzu.waqt.model.AllTask;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Drac Android on 2/10/2017.
 */

       /* We are using the adapter similar to the one we used in our previous projects.
        We pass Androidversion model class as ArrayList in constructor.
        We obtain the Android version name, version number, API level from the AllTask object
        using the getter methods and set it to TextView widgets.*/

public class IftarDataAdapter extends RecyclerView.Adapter<IftarDataAdapter.ViewHolder> {
    private ArrayList<AllTask> iftar;


    public IftarDataAdapter(ArrayList<AllTask> iftar) {
        this.iftar = iftar;
    }

    @Override
    public IftarDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.iftar_card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IftarDataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.iftar_sn.setText(iftar.get(i).getSn());
        viewHolder.iftar_day.setText(iftar.get(i).getDay());
        viewHolder.iftar_date.setText(iftar.get(i).getIdate());
        viewHolder.iftar_time.setText(iftar.get(i).getItime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");


        if ((iftar.get(i).getIdate().equals(df))) {
            viewHolder.iftar_day.setText(iftar.get(i).getDay());
            viewHolder.iftar_sn.setText(iftar.get(i).getSn());
            viewHolder.iftar_date.setText(iftar.get(i).getIdate());
            viewHolder.iftar_time.setText(iftar.get(i).getItime());
            viewHolder.iftar_day.setBackgroundColor(Color.parseColor("#FF150184"));
        }
        else{
            viewHolder.iftar_sn.setText(iftar.get(i).getSn());
            viewHolder.iftar_day.setText(iftar.get(i).getDay());
            viewHolder.iftar_date.setText(iftar.get(i).getIdate());
            viewHolder.iftar_time.setText(iftar.get(i).getItime());
        }
    }

    @Override
    public int getItemCount() {
        return iftar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.iftar_sn)
        TextView iftar_sn;
        @BindView(R.id.iftar_day)
        TextView iftar_day;
        @BindView(R.id.iftar_date)
        TextView iftar_date;
        @BindView(R.id.iftar_time)
        TextView iftar_time;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);


        }
    }

}




















































/*

public class IftarDataAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<AllTask> iftar;
    Context c;
     @BindView(R.id.iftar_date) TextView date;

    public IftarDataAdapter(Context c, ArrayList<AllTask> iftar) {
        this.c = c;
        this.iftar = iftar;

    }

    */
/*@Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(c)
                .inflate(R.layout.iftar_card_row, viewGroup, false);
        return new MyViewHolder(view);
    }*//*


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c)
                .inflate(R.layout.iftar_card_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AllTask allTask= iftar.get(position);


        final  String sn=allTask.getSn();
       final  String date=allTask.getDate();
       final  String day=allTask.getDay();
       final  String time=allTask.getDay();


      //  holder.iftar_sn.setText(iftar.get(position).getSn());
//        viewHolder.iftar_day.setText(iftar.get(position).getDay());
//        viewHolder.iftar_date.setText(iftar.get(position).getIdate());
//        viewHolder.iftar_time.setText(iftar.get(position).getItime());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openNext(sn,date,day,time);
            }
        });
    }

//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//
//    }

//    @Override
//    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
//
//        AllTask allTask= iftar.get(position);
//
//        final  String sn=allTask.getSn();
//        final  String date=allTask.getDate();
//        final  String day=allTask.getDay();
//        final  String time=allTask.getDay();
//
//
//        //BIND
//        viewHolder.iftar_sn.setText(sn);
//        viewHolder.iftar_date.setText(date);
//        viewHolder.iftar_day.setText(day);
//        viewHolder.iftar_time.setText(time);
//
//        viewHolder.setItemClickListner(new ItemClickListener() {
//            @Override
//            public void onItemClick(int pos) {
//
//            }
//        });


//
//        viewHolder.iftar_sn.setText(ifta.get(position).getSn());
//        viewHolder.iftar_day.setText(iftar.get(position).getDay());
//        viewHolder.iftar_date.setText(iftar.get(position).getIdate());
//        viewHolder.iftar_time.setText(iftar.get(position).getItime());
      //  viewHolder.iftar_dua.setText(iftar.get(i).getIdua());



    @Override
    public int getItemCount() {
        return iftar.size();
    }

    private  void openNext(String... details){
        Intent i = new Intent(c, Ramzan.class);
        i.putExtra("SN_KEY", details[0]);
        i.putExtra("DATE_KEY", details[1]);
        i.putExtra("DAY_KEY", details[2]);
        i.putExtra("TIME_KEY", details[3]);
        c.startActivity(i);
    }

//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private TextView iftar_sn,iftar_day, iftar_date,iftar_time, iftar_dua;
//        public ViewHolder(View view) {
//            super(view);
//
//            iftar_sn =(TextView)view.findViewById(R.id.iftar_sn);
//            iftar_day = (TextView)view.findViewById(R.id.iftar_day);
//            iftar_date = (TextView)view.findViewById(R.id.iftar_date);
//            iftar_time = (TextView)view.findViewById(R.id.iftar_time);
//          //  iftar_dua = (TextView)view.findViewById(R.id.iftar_dua);
//
//        }
//    }

}*/
