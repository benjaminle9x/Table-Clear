package com.n01216688.testing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final List<CardItem> mDataList;

    public interface RecyclerViewClickListener{
        void onItemClicked(int position);
    }

    private RecyclerViewClickListener mListener;

    public void setOnClickListener(RecyclerViewClickListener listener){
        mListener = listener;
    }

    public RecyclerAdapter(List<CardItem> dataList){
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItem item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.iconDrawable.setImageDrawable(item.getIconDrawable()) ;

        if(mListener != null ){
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    mListener.onItemClicked(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconDrawable;
        TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            iconDrawable = itemView.findViewById(R.id.icon);
        }
    }


}
