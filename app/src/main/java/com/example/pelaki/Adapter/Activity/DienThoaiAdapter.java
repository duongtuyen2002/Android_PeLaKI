package com.example.pelaki.Adapter.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.MainActivity;
import com.example.pelaki.Model.Acivity.DienThoai;
import com.example.pelaki.Model.Acivity.GioHang;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.ViewHolder> {
    String URL_INSERT = "http://loyalist002-001-site1.gtempurl.com/insertgiohang.php";
    Context context;
    ArrayList<DienThoai> listDienthoai;


    TextView ten,gia,back;
    Button themvaogio;
    ImageView anh;
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
        ten.setText(dienThoai.getTen());
        gia.setText(decimalFormat.format(dienThoai.getGia())+ "Đ");
        Picasso.with(context).load(dienThoai.getHinhanh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(anh);
        themvaogio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl =1;
                if(MainActivity.listgiohang.size() >0){
                    boolean exits = false;
                    for(int i =0; i <MainActivity.listgiohang.size(); i++){
                            if(MainActivity.listgiohang.get(i).getTen() == dienThoai.getTen()){
                                MainActivity.listgiohang.get(i).setSoluong(MainActivity.listgiohang.get(i).getSoluong() + sl);
                                MainActivity.listgiohang.get(i).setGia(dienThoai.getGia() * MainActivity.listgiohang.get(i).getSoluong());
                                Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                                exits = true;
                            }
                    } if(exits == false){
                        int soluong1 = 1;
                        long giamoi = soluong1 * dienThoai.getGia();
                        MainActivity.listgiohang.add(new GioHang(dienThoai.getId(),dienThoai.getTen(),soluong1,giamoi, dienThoai.getHinhanh()));
                        Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    int soluong = 1;
                    long giamoi = soluong * dienThoai.getGia();
                    MainActivity.listgiohang.add(new GioHang(dienThoai.getId(),dienThoai.getTen(),soluong,giamoi, dienThoai.getHinhanh()));
                    Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tv_tendt);
            gia = itemView.findViewById(R.id.tv_giadt);
            anh = itemView.findViewById(R.id.img_dienthoai);
            back = itemView.findViewById(R.id.tv_back_dt);
            themvaogio = itemView.findViewById(R.id.themgiohangdt);
        }
    }

}
