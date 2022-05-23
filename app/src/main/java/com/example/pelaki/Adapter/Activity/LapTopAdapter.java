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

import com.example.pelaki.MainActivity;
import com.example.pelaki.Model.Acivity.GioHang;
import com.example.pelaki.Model.Acivity.LapTop;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LapTopAdapter extends RecyclerView.Adapter<LapTopAdapter.ViewHolder> {

    Context context;
    ArrayList<LapTop> listLap;

    TextView ten,gia,back,giohang;
    ImageView anh;
    Button themvaogio;
    public LapTopAdapter(Context context, ArrayList<LapTop> listLap) {
        this.context = context;
        this.listLap = listLap;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dong_laptop,null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        LapTop lapTop = listLap.get(position);
        ten.setText(lapTop.getTen());
        gia.setText(decimalFormat.format(lapTop.getGia())+ "Đ");
        Picasso.with(context).load(lapTop.getHinhanh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(anh);
        themvaogio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl =1;
                if(MainActivity.listgiohang.size() >0){
                    boolean exits = false;
                    for(int i =0; i <MainActivity.listgiohang.size(); i++){
                        if(MainActivity.listgiohang.get(i).getTen() == lapTop.getTen()){
                            MainActivity.listgiohang.get(i).setSoluong(MainActivity.listgiohang.get(i).getSoluong() + sl);
                            MainActivity.listgiohang.get(i).setGia(lapTop.getGia() * MainActivity.listgiohang.get(i).getSoluong());
                            exits = true;
                        }
                    } if(exits == false){
                        int soluong1 = 1;
                        long giamoi = soluong1 * lapTop.getGia();
                        MainActivity.listgiohang.add(new GioHang(lapTop.getId(),lapTop.getTen(),soluong1,giamoi, lapTop.getHinhanh()));
                    }
                }else {
                    int soluong = 1;
                    long giamoi = soluong * lapTop.getGia();
                    MainActivity.listgiohang.add(new GioHang(lapTop.getId(),lapTop.getTen(),soluong,giamoi, lapTop.getHinhanh()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLap.size();
    }

    public void filterSP(ArrayList<LapTop> filterList) {
        listLap = filterList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_tenlap);
            gia = itemView.findViewById(R.id.tv_gialap);
            anh = itemView.findViewById(R.id.img_lap);
            back = itemView.findViewById(R.id.tv_back_lt);
            giohang = itemView.findViewById(R.id.tv_giohanglt);
            themvaogio = itemView.findViewById(R.id.themgiohanglap);
        }
    }

}
