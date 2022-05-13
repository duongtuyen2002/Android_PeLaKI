package com.example.pelaki.Adapter.HOME;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Model.HOME.NapTheVaDichVuHome;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NapTheVaDichVuAdapter extends RecyclerView.Adapter<NapTheVaDichVuAdapter.ItemHolder> {
    Context context;
    ArrayList<NapTheVaDichVuHome> napTheVaDichVuHomes;

    public NapTheVaDichVuAdapter(Context context, ArrayList<NapTheVaDichVuHome> napTheVaDichVuHomes) {
        this.context = context;
        this.napTheVaDichVuHomes = napTheVaDichVuHomes;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_dichvu,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        NapTheVaDichVuHome dichvu = napTheVaDichVuHomes.get(position);
        holder.gia.setText(decimalFormat.format(dichvu.getGia())+ "Đ");
        holder.tendichvu.setText(dichvu.getTendichvu());
        holder.tenhang.setText(dichvu.getTenhang());
        Picasso.with(context).load(dichvu.getAnh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.anh);
    }

    @Override
    public int getItemCount() {
        return napTheVaDichVuHomes.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView gia, tenhang, tendichvu;
        public ImageView anh;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            gia = itemView.findViewById(R.id.tenloaithucan);
            anh = itemView.findViewById(R.id.iconThucAn);
            tendichvu = itemView.findViewById(R.id.tendichvu);
            tenhang = itemView.findViewById(R.id.tenhang);
        }
    }
}
