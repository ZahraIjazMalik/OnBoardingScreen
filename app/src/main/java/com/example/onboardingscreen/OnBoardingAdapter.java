package com.example.onboardingscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.MyViewHolder> {
    Context context;
    List<OnBoardingData> list ;
    public OnBoardingAdapter(Context context, ArrayList<OnBoardingData> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.onboardingactivity, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OnBoardingData data = list.get(position);
        holder.img.setImageResource(data.getImg());
        holder.title.setText(data.getTitle());
        holder.details.setText(data.getDetails());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title, details;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.itemIV);
            title = itemView.findViewById(R.id.titleTV);
            details = itemView.findViewById(R.id.detailsTV);
        }
    }
}