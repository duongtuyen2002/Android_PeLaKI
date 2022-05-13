package com.example.pelaki.Adapter.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Model.Acivity.DienThoai;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.ViewHolder> {

    Context context;
    ArrayList<DienThoai> listDienthoai;

    public DienThoaiAdapter(Context context, ArrayList<DienThoai> listDienthoai) {
        this.context = context;
        this.listDienthoai = listDienthoai;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dong_dienthoai,null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        DienThoai dienThoai = listDienthoai.get(position);
        holder.ten.setText(dienThoai.getTen());
        holder.gia.setText(decimalFormat.format(dienThoai.getGia())+ "Đ");
        Picasso.with(context).load(dienThoai.getHinhanh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.anh);
//        holder.back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, HomeFragment.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listDienthoai.size();
    }

    public void filterSP(ArrayList<DienThoai> filterList) {
        listDienthoai = filterList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ten,gia,back,giohang;
        ImageView anh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_tendt);
            gia = itemView.findViewById(R.id.tv_giadt);
            anh = itemView.findViewById(R.id.img_dienthoai);
//            back = itemView.findViewById(R.id.tv_back_dt);
//            giohang = itemView.findViewById(R.id.tv_giohang);
        }
    }

}
