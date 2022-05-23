package com.example.pelaki.Fragment.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelaki.Adapter.Activity.GioHangAdapter;
import com.example.pelaki.MainActivity;
import com.example.pelaki.R;

import java.text.DecimalFormat;

public class Activity_GioHang_Food extends FragmentActivity {
    RecyclerView recyclerView;
    GioHangAdapter adapter;

    TextView tongtien;
    Button btnmua,btntieptucmua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang_food);

        long tong = 0;
        recyclerView = findViewById(R.id.Recy_giohangfood);
        adapter = new GioHangAdapter(getApplicationContext(), MainActivity.listGiohangFood);
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
        tongtien = findViewById(R.id.tongtien);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        for (int i = 0; i < MainActivity.listGiohangFood.size(); i++){
            tong += MainActivity.listGiohangFood.get(i).getGia();
        }
        tongtien.setText(decimalFormat.format(tong) + "Đ");

        btnmua = findViewById(R.id.buttondathang);
        btntieptucmua = findViewById(R.id.buttontieptuc);

        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
