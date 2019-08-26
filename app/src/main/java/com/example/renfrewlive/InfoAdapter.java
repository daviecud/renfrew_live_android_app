package com.example.renfrewlive;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private Context mCtx;
    private List<InfoDetails> infoList; //this is assigning details into List called infoList

    //Constructor
    public InfoAdapter(Context mCtx, List<InfoDetails> infoList) {
        this.mCtx = mCtx;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.useful_info_card , null);
        InfoViewHolder holder = new InfoViewHolder(view);
        return new InfoViewHolder(view);
    }

    @Override // will bind items to infoViewholder
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        InfoDetails info = infoList.get(position);

        holder.textViewName.setText(info.getInfo_name());
        holder.textViewAddress.setText(info.getInfo_address());
        holder.textViewTel.setText(info.getInfo_tel());
        holder.textViewSummary.setText(info.getInfo_summary());

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(info.getImage(), null));
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {
        //Creates the UI elements
        ImageView imageView;
        TextView textViewName, textViewAddress, textViewTel, textViewSummary;


        public InfoViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.info_image);
                textViewName = itemView.findViewById(R.id.info_name);
                textViewAddress = itemView.findViewById(R.id.info_address);
                textViewTel = itemView.findViewById(R.id.info_tel);
                textViewSummary = itemView.findViewById(R.id.info_summary);

            }
        }

}
