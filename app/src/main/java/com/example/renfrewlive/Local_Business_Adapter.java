package com.example.renfrewlive;

/*
* Need to set RecyclerView.Adapter
* Need to set RecyclerView.ViewHolder
* */

//1st thing to do after create of classes is to inflate onCreate with LayoutInflater
//2nd create UI elements in Local_BusinessViewHolder class
//3rd set getItemCount to return list.size()


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Local_Business_Adapter extends RecyclerView.Adapter<Local_Business_Adapter.Local_BusinessViewholder> {

    private Context context;
    private List<Local_Business> localBusList;

    public Local_Business_Adapter(Context context, List<Local_Business> localBusList) {
        this.context = context;
        this.localBusList = localBusList;
    }

    @NonNull
    @Override
    public Local_BusinessViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.local_business_layout, null);
        return new Local_BusinessViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Local_BusinessViewholder holder, int position) {
        Local_Business localBus = localBusList.get(position);

        holder.textViewName.setText(localBus.getBusiness_name());
        holder.textViewAddress.setText(localBus.getBusiness_address());
        holder.textViewTel.setText(localBus.getBusiness_tel());
        holder.textViewWeb.setText(localBus.getBusiness_web());
        holder.textViewSummary.setText(localBus.getBusiness_summary());

//        holder.imageView.setImageDrawable(context.getResources().getDrawable(localBus.getImage(), null));

    }

    @Override
    public int getItemCount() {
        return localBusList.size();
    }


    class Local_BusinessViewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewAddress, textViewTel, textViewWeb, textViewSummary;

        public Local_BusinessViewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.business_logo);
            textViewName = itemView.findViewById(R.id.business_name);
            textViewAddress = itemView.findViewById(R.id.business_address);
            textViewTel = itemView.findViewById(R.id.business_tel);
            textViewWeb = itemView.findViewById(R.id.business_web);
            textViewSummary = itemView.findViewById(R.id.business_summary);
        }
    }
}
