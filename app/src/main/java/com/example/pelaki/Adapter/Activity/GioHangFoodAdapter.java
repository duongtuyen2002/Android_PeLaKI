package com.example.pelaki.Adapter.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Model.Acivity.GioHang;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangFoodAdapter extends RecyclerView.Adapter<GioHangFoodAdapter.ViewHolder> {
    Context context;
    ArrayList<GioHang> gioHangs;

    public GioHangFoodAdapter(Context context, ArrayList<GioHang> gioHangs) {
        this.context = context;
        this.gioHangs = gioHangs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dong_giohang,null);
        ViewHolder viewHolder = new GioHangFoodAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang gioHang = gioHangs.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tensp.setText(gioHang.getTen());
        holder.gia.setText(decimalFormat.format(gioHang.getGia()) + "Đ");
        Picasso.with(context).load(gioHang.getHinhanh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.anh);
        holder.btnsl.setText(gioHang.getSoluong() + "");
    }

    @Override
    public int getItemCount() {
        return gioHangs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tensp,gia;
        ImageView anh;
        Button btntru, btnsl, btncong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.tv_tensp);
            gia = itemView.findViewById(R.id.tv_giasp);
            anh = itemView.findViewById(R.id.img_giohang);
            btntru = itemView.findViewById(R.id.btn_tru);
            btnsl = itemView.findViewById(R.id.tv_giatri);
            btncong = itemView.findViewById(R.id.btn_cong);
        }
    }
}
