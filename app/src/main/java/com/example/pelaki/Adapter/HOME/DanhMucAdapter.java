package com.example.pelaki.Adapter.HOME;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Model.HOME.DanhMucHome;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.ItemHolder> {
    Context context;
    ArrayList<DanhMucHome> danhMucArrayList;

    public DanhMucAdapter(Context context, ArrayList<DanhMucHome> danhMucArrayList) {
        this.context = context;
        this.danhMucArrayList = danhMucArrayList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dongdanhmuchome,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        DanhMucHome danhMuc = danhMucArrayList.get(position);
        holder.tenLoaiSP.setText(danhMuc.getTen());
        Picasso.with(context).load(danhMuc.getAnh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(holder.anhLoaiSP);
    }

    @Override
    public int getItemCount() {
        return danhMucArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
       public TextView tenLoaiSP;
       public ImageView anhLoaiSP;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tenLoaiSP = itemView.findViewById(R.id.tenloaidanhmuc);
            anhLoaiSP = itemView.findViewById(R.id.iconDanhMuc);
        }
    }
}
