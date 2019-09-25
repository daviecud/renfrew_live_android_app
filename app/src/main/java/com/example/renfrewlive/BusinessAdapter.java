package com.example.renfrewlive;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

import java.util.List;

/*
* RecyclerView.Adapter - this binds the data to the view
* RecyclerViewHolder.ViewHolder - this holds the view from card.xml
* */

public class BusinessAdapter extends FirestoreRecyclerAdapter<BusinessDetails, BusinessAdapter.BusinessHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BusinessAdapter(@NonNull FirestoreRecyclerOptions<BusinessDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BusinessHolder holder, int position, @NonNull BusinessDetails model) {
        holder.textViewBusName.setText(model.getBus_name());
        holder.textViewBusAdd.setText(model.getBus_address());
        holder.textViewBusTel.setText(model.getBus_tel());
        holder.textViewBusWeb.setText(model.getBus_summary());
        holder.textViewBusSummary.setText(model.getBus_summary());

    }

    @NonNull
    @Override
    public BusinessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_card, parent, false);
        return new BusinessHolder(v);
    }


    class BusinessHolder extends RecyclerView.ViewHolder {

        TextView textViewBusName;
        TextView textViewBusAdd;
        TextView textViewBusTel;
        TextView textViewBusWeb;
        TextView textViewBusSummary;

        public BusinessHolder(@NonNull View itemView) {
            super(itemView);
            textViewBusName = itemView.findViewById(R.id.bus_name);
            textViewBusAdd = itemView.findViewById(R.id.bus_address);
            textViewBusTel = itemView.findViewById(R.id.bus_tel);
            textViewBusWeb = itemView.findViewById(R.id.bus_web);
            textViewBusSummary = itemView.findViewById(R.id.bus_summary);
        }
    }
}

/*
    @Override //this will create a Viewholder from an instance of class BusinessViewHolder (below) UI elements
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.business_card, null);
        return new BusinessViewHolder(view); //this will be passed to class BusinessViewHolder (below
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        BusinessDetails businessDetails = businessList.get(position);

        holder.textViewBusName.setText(businessDetails.getBus_name());
        holder.textViewBusAddress.setText(businessDetails.getBus_address());
        holder.textViewBusTel.setText(businessDetails.getBus_tel());
        holder.textViewBusWeb.setText(businessDetails.getBus_web());
        holder.textViewSummary.setText(businessDetails.getBus_summary());

        holder.imageView.setImageDrawable(context.getResources().getDrawable(businessDetails.getBus_image(), null));
    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }




    class BusinessViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewBusName, textViewBusAddress, textViewBusTel, textViewBusWeb, textViewSummary;

        public BusinessViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.bus_logo);
            textViewBusName = itemView.findViewById(R.id.bus_name);
            textViewBusAddress = itemView.findViewById(R.id.bus_address);
            textViewBusTel = itemView.findViewById(R.id.bus_tel);
            textViewBusWeb = itemView.findViewById(R.id.bus_web);
            textViewSummary = itemView.findViewById(R.id.bus_summary);
        }
    }*/