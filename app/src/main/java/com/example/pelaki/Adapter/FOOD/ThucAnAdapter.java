package com.example.pelaki.Adapter.FOOD;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Model.FOOD.ThucAn;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThucAnAdapter  extends RecyclerView.Adapter<ThucAnAdapter.ItemHolder> {
    Context context;
    ArrayList<ThucAn> foodArrayList;

    public ThucAnAdapter(Context context, ArrayList<ThucAn> foodArrayList) {
        this.context = context;
        this.foodArrayList = foodArrayList;
    }

    @NonNull
    @Override
    public ThucAnAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dong_thucan,null);
        ThucAnAdapter.ItemHolder itemHolder = new ThucAnAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThucAnAdapter.ItemHolder holder, int position) {
        ThucAn thucAn = foodArrayList.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.ten.setText(thucAn.getTen());
        holder.gia.setText("Giá: " + decimalFormat.format(thucAn.getGia())+"Đ");
        Picasso.with(context).load(thucAn.getHinhanh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.anh);
        holder.anh.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView ten,gia;
        public ImageView anh;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenthucan);
            gia = itemView.findViewById(R.id.giathucan);
            anh = itemView.findViewById(R.id.imgThucAn);
        }
    }
}
