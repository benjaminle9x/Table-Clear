package com.n01216688.testing;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RestaurantlistAdapter extends RecyclerView.Adapter<RestaurantlistAdapter.ViewHolder>{
    private static final String TAG = "RestaurantlistAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mScores = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    public RestaurantlistAdapter(Context mContext, ArrayList<String> mImageNames, ArrayList<String> mScores, ArrayList<String> mImages) {
        this.mImageNames = mImageNames;
        this.mScores = mScores;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called.");
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));
        holder.resScore.setText(mScores.get(position));

        holder.res_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Log.d(TAG,"onClick: clicked on: " + mImageNames.get(position));
                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageName, resScore;
        RelativeLayout res_layout;

        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.img);
            imageName = itemView.findViewById(R.id.img_name);
            resScore = itemView.findViewById(R.id.score);
            res_layout = itemView.findViewById(R.id.res_layout);
        }
    }
}
