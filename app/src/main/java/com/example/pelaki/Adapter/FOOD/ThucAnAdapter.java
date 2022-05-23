package com.example.pelaki.Adapter.FOOD;

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
import com.example.pelaki.Model.Acivity.GioHang;
import com.example.pelaki.Model.FOOD.ThucAn;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThucAnAdapter  extends RecyclerView.Adapter<ThucAnAdapter.ItemHolder> {
    Context context;
    ArrayList<ThucAn> foodArrayList;

    public TextView ten,gia;
    public ImageView anh;
    Button themdonhang;

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
        ten.setText(thucAn.getTen());
        gia.setText("Giá: " + decimalFormat.format(thucAn.getGia())+"Đ");
        Picasso.with(context).load(thucAn.getHinhanh()).placeholder(R.drawable.noimage).error(R.drawable.erro).into(anh);
        anh.setScaleType(ImageView.ScaleType.FIT_XY);
        themdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl =1;
                if(MainActivity.listGiohangFood.size() >0){
                    boolean exits = false;
                    for(int i =0; i <MainActivity.listGiohangFood.size(); i++){
                        if(MainActivity.listGiohangFood.get(i).getTen() == thucAn.getTen()){
                            MainActivity.listGiohangFood.get(i).setSoluong(MainActivity.listGiohangFood.get(i).getSoluong() + sl);
                            MainActivity.listGiohangFood.get(i).setGia(thucAn.getGia() * MainActivity.listGiohangFood.get(i).getSoluong());
                            Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                            exits = true;
                        }
                    } if(exits == false){
                        int soluong1 = 1;
                        long giamoi = soluong1 * thucAn.getGia();
                        MainActivity.listGiohangFood.add(new GioHang(thucAn.getId(),thucAn.getTen(),soluong1,giamoi, thucAn.getHinhanh()));
                        Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    int soluong = 1;
                    long giamoi = soluong * thucAn.getGia();
                    MainActivity.listGiohangFood.add(new GioHang(thucAn.getId(),thucAn.getTen(),soluong,giamoi, thucAn.getHinhanh()));
                    Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public void filterSP(ArrayList<ThucAn> filterList) {
        foodArrayList = filterList;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenthucan);
            gia = itemView.findViewById(R.id.giathucan);
            anh = itemView.findViewById(R.id.imgThucAn);
            themdonhang = itemView.findViewById(R.id.btn_themfood);
        }
    }
}
