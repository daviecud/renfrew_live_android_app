package com.example.renfrewlive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class DataAdapter extends FirestoreRecyclerAdapter<DataDetails, DataAdapter.DataHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DataAdapter(@NonNull FirestoreRecyclerOptions<DataDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DataHolder holder, int position, @NonNull DataDetails model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getDescription());
        holder.textViewPriority.setText(String.valueOf(model.getPriority()));
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);

        return new DataHolder(v);
    }

    class DataHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPriority;


        public DataHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView_Title);
            textViewDescription = itemView.findViewById(R.id.textView_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }
}
