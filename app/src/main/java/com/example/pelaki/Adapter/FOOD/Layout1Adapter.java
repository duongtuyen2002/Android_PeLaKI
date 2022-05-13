package com.example.pelaki.Adapter.FOOD;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Model.FOOD.Layout1Food;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Layout1Adapter extends RecyclerView.Adapter<Layout1Adapter.ItemHolder> {
    Context context;
    ArrayList<Layout1Food> foodArrayList;

    public Layout1Adapter(Context context, ArrayList<Layout1Food> foodArrayList) {
        this.context = context;
        this.foodArrayList = foodArrayList;
    }

    @NonNull
    @Override
    public Layout1Adapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dong_layout1food,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Layout1Adapter.ItemHolder holder, int position) {
        Layout1Food layout1Food = foodArrayList.get(position);
        holder.ten.setText(layout1Food.getTen());
        Picasso.with(context).load(layout1Food.getAnh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.anh);

    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView ten;
        public ImageView anh;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenloaithucan);
            anh = itemView.findViewById(R.id.iconThucAn);
        }
    }
}
