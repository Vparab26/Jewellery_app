package com.example.kisan_suvidha.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kisan_suvidha.R;
import com.example.kisan_suvidha.activities.NavCategoryActivity;
import com.example.kisan_suvidha.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapters extends RecyclerView.Adapter<NavCategoryAdapters.ViewHolder> {

    Context context;
    List<NavCategoryModel> list;

    public NavCategoryAdapters(Context context, List<NavCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    public NavCategoryAdapters() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.discount.setText(list.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("type",list.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nav_img);
            name = itemView.findViewById(R.id.cat_name);
            description = itemView.findViewById(R.id.cat_desc);
            discount = itemView.findViewById(R.id.cat_disc);

        }
    }
}
