package com.example.pelaki.Adapter.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Fragment.Activity.Activity_GioHang;
import com.example.pelaki.MainActivity;
import com.example.pelaki.Model.Acivity.GioHang;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    Context context;
    ArrayList<GioHang> gioHangs;

    public GioHangAdapter(Context context, ArrayList<GioHang> gioHangs) {
        this.context = context;
        this.gioHangs = gioHangs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dong_giohang,null);
        ViewHolder viewHolder = new GioHangAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GioHang gioHang = gioHangs.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tensp.setText(gioHang.getTen());
        holder.gia.setText(decimalFormat.format(gioHang.getGia()) + "Đ");
        Picasso.with(context).load(gioHang.getHinhanh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.anh);
        holder.sl.setText(gioHang.getSoluong()+"");
        int sl = Integer.parseInt(holder.sl.getText().toString());
        if(sl <2 ){
            holder.btntru.setVisibility(View.INVISIBLE);
         }else {
        holder.btntru.setVisibility(View.VISIBLE);
        }
        holder.btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi = Integer.parseInt(holder.sl.getText().toString()) +1;
                int slht = MainActivity.listgiohang.get(position).getSoluong();
                long giaht = MainActivity.listgiohang.get(position).getGia();
                MainActivity.listgiohang.get(position).setSoluong(slmoi);
                long giamoi = (giaht * slmoi) / slht;
                DecimalFormat format = new DecimalFormat("###,###,###");
                holder.gia.setText(format.format(giamoi) +"Đ");
                holder.sl.setText(slmoi + "");
                Activity_GioHang.Enven();
                if(slmoi > 1){
                    holder.btntru.setVisibility(View.VISIBLE);
                    MainActivity.listgiohang.get(position).setGia(giamoi);
                }else {
                    holder.btntru.setVisibility(View.INVISIBLE);
                    MainActivity.listgiohang.get(position).setGia(giamoi);
                }
            }
        });
        
        holder.btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi = Integer.parseInt(holder.sl.getText().toString()) - 1;
                int slht = MainActivity.listgiohang.get(position).getSoluong();
                long giaht = MainActivity.listgiohang.get(position).getGia();
                MainActivity.listgiohang.get(position).setSoluong(slmoi);
                long giamoi1 = (giaht * slmoi)/slht;
                MainActivity.listgiohang.get(position).setGia(giamoi1);
                DecimalFormat format = new DecimalFormat("###,###,###");
                holder.gia.setText(format.format(giamoi1) +"Đ");
                holder.sl.setText(slmoi + "");
                Activity_GioHang.Enven();
                if(slmoi <2){
                    holder.btntru.setVisibility(View.INVISIBLE);
                    MainActivity.listgiohang.get(position).setGia(giamoi1);
                }else {
                    holder.btntru.setVisibility(View.VISIBLE);
                    MainActivity.listgiohang.get(position).setGia(giamoi1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tensp,gia,sl;
        ImageView anh;
        Button btntru, btncong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.tv_tensp);
            gia = itemView.findViewById(R.id.tv_giasp);
            anh = itemView.findViewById(R.id.img_giohang);
            btntru = itemView.findViewById(R.id.btn_tru);
            sl = itemView.findViewById(R.id.tv_giatri);
            btncong = itemView.findViewById(R.id.btn_cong);

        }
    }
}
