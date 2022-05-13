package com.example.pelaki.Adapter.Noti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Model.Noti.Noti;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.ViewHolder> {
    Context context;
    ArrayList<Noti> notiArrayList;

    public NotiAdapter(Context context, ArrayList<Noti> notiArrayList) {
        this.context = context;
        this.notiArrayList = notiArrayList;
    }

    @NonNull
    @Override
    public NotiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_noti,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotiAdapter.ViewHolder holder, int position) {
        Noti noti = notiArrayList.get(position);
        holder.noidung.setText(noti.getNoidung());
        holder.thongbao.setText(noti.getThongbao());
        Picasso.with(context).load(noti.getImg()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.img);
        holder.img.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public int getItemCount() {
        return notiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView thongbao,noidung;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_thongbao);
            thongbao = itemView.findViewById(R.id.tv_thongbao);
            noidung = itemView.findViewById(R.id.tv_noidungthongbao);
        }
    }
}
