package com.example.pelaki.Adapter.HOME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Fragment.Activity.Activity_DienThoai;
import com.example.pelaki.Fragment.Activity.Activity_Lap;
import com.example.pelaki.Model.HOME.TimKiemHangDauHome;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TimKiemHangDauAdapter extends RecyclerView.Adapter<TimKiemHangDauAdapter.ItemHolder> {
    Context context;
    ArrayList<TimKiemHangDauHome> timkiemhangdauList;

    public TimKiemHangDauAdapter(Context context, ArrayList<TimKiemHangDauHome> timkiemhangdauList) {
        this.context = context;
        this.timkiemhangdauList = timkiemhangdauList;

    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_timkiemhangdau_home,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, @SuppressLint("RecyclerView") int position) {
        TimKiemHangDauHome timKiemHangDauHome = timkiemhangdauList.get(position);
        holder.ten.setText(timKiemHangDauHome.getTen());
        Picasso.with(context).load(timKiemHangDauHome.getAnh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.anh);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        Intent lt = new Intent(context, Activity_Lap.class);
                        context.startActivity(lt);
                        break;
                    case 1:
                        Intent dt = new Intent(context, Activity_DienThoai.class);
                        context.startActivity(dt);
                        break;
                    default:
                        Intent khac = new Intent(context, Activity_DienThoai.class);
                        context.startActivity(khac);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return timkiemhangdauList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        public TextView ten;
        public ImageView anh;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.anhTimKiemHangDau);
            ten = itemView.findViewById(R.id.tenTimKiemHangDau);
            layout = itemView.findViewById(R.id.rlt_timkiem);
        }
    }
}