package com.example.renfrewlive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class LocalBusinessAdapter extends FirestoreRecyclerAdapter<LocalBusinessDetails, LocalBusinessAdapter.LocalBusinessHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public LocalBusinessAdapter(@NonNull FirestoreRecyclerOptions<LocalBusinessDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull LocalBusinessHolder holder, int position, @NonNull LocalBusinessDetails model) {

        holder.textViewName.setText(model.getName());
        holder.textViewAddress.setText(model.getAddress());
        holder.textViewTelephone.setText(model.getTelephone());
        holder.textViewWeb.setText(model.getWeb());
        holder.textViewSummary.setText(model.getSummary());
    }

    @NonNull
    @Override
    public LocalBusinessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.local_bus_card, parent, false);

        return new LocalBusinessHolder(v);
    }


    class LocalBusinessHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewAddress;
        TextView textViewTelephone;
        TextView textViewWeb;
        TextView textViewSummary;

        public LocalBusinessHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.bus_name);
            textViewAddress = itemView.findViewById(R.id.bus_address);
            textViewTelephone = itemView.findViewById(R.id.bus_tel);
            textViewWeb = itemView.findViewById(R.id.bus_web);
            textViewSummary = itemView.findViewById(R.id.bus_summary);
        }
    }
}
