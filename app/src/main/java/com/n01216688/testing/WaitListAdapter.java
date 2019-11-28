package com.n01216688.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WaitListAdapter extends RecyclerView.Adapter<WaitListAdapter.ExampleViewHolder> {
    private ArrayList<WaitListStructure> mWaitList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mcName;
        public TextView mcPhone;
        public TextView mcSize;
        public TextView mrName;
        public TextView mcTime;
        public TextView mcTable;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mcName = itemView.findViewById(R.id.c_name);
            mcPhone = itemView.findViewById(R.id.c_phone);
            mcSize = itemView.findViewById(R.id.c_size);
            mrName = itemView.findViewById(R.id.c_restaurant);
            mcTime = itemView.findViewById(R.id.c_time);
            mcTable = itemView.findViewById(R.id.c_tableno);
        }
    }

    public WaitListAdapter(ArrayList<WaitListStructure> waitlist) {
        mWaitList = waitlist;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.waitlist, parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        WaitListStructure currentItem = mWaitList.get(position);
        holder.mcName.setText(currentItem.getCname());
        holder.mcPhone.setText(currentItem.getCphone());
        holder.mcSize.setText(currentItem.getCsize());
        holder.mrName.setText(currentItem.getRname());
        holder.mcTime.setText(currentItem.getCtime());
        holder.mcTable.setText(currentItem.getCtable());
    }

    @Override
    public int getItemCount() {
        return mWaitList.size();
    }
}
