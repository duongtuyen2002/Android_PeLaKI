package com.example.pelaki.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pelaki.Adapter.HOME.DanhMucAdapter;
import com.example.pelaki.Adapter.HOME.NapTheVaDichVuAdapter;
import com.example.pelaki.Adapter.HOME.TimKiemHangDauAdapter;
import com.example.pelaki.Model.HOME.DanhMucHome;
import com.example.pelaki.Model.HOME.NapTheVaDichVuHome;
import com.example.pelaki.Model.HOME.TimKiemHangDauHome;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    String URLDanhMuc = "http://loyalist002-001-site1.gtempurl.com/jsondanhmuc.php";
    String URLTimKiem ="http://loyalist002-001-site1.gtempurl.com/jsontimkiemhangdau.php";
    String URLDichVu = "http://loyalist002-001-site1.gtempurl.com/jsondichvu.php";
    ViewFlipper vFlipper,vFlipper2;
    RecyclerView recyclerView,recyclerView2,recyclerView3;
    ArrayList<DanhMucHome> danhMucArrayList;
    DanhMucAdapter danhMucAdapter;

    ArrayList<TimKiemHangDauHome> listTimKiem;
    TimKiemHangDauAdapter timkiemAdapter;

    ArrayList<NapTheVaDichVuHome> listDichVu;
    NapTheVaDichVuAdapter dichVuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        vFlipper = view.findViewById(R.id.vFliper);
        vFlipper2 = view.findViewById(R.id.vFliperDichVU);
        //Ánh Xạ Danh Mục
        recyclerView = view.findViewById(R.id.recyDanhMucHang);
        danhMucArrayList = new ArrayList<>();
        danhMucAdapter = new DanhMucAdapter(getContext(),danhMucArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(danhMucAdapter);

        //Ánh xạ tìm kiếm
        recyclerView2 = view.findViewById(R.id.recyTimKiem);
        listTimKiem = new ArrayList<>();
        timkiemAdapter = new TimKiemHangDauAdapter(getContext(),listTimKiem);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(LayoutManager);
        recyclerView2.setAdapter(timkiemAdapter);

        //Ánh xạ dịch vụ
        recyclerView3 = view.findViewById(R.id.recyNaptheVaDichvu);
        listDichVu = new ArrayList<>();
        dichVuAdapter = new NapTheVaDichVuAdapter(getContext(),listDichVu);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layout);
        recyclerView3.setAdapter(dichVuAdapter);
        viewFlipper();
        viewFlipperdichVu();
        getDanhMuc(URLDanhMuc);
        getTimKiem(URLTimKiem);
        getDichVu(URLDichVu);
        return view;
    }

    private void viewFlipperdichVu() {
        ArrayList<String> quangCao = new ArrayList<>();
        quangCao.add("https://cdn.tgdd.vn/Files/2014/04/14/542019/bannerWeb_thecao-02.jpg");
        quangCao.add("https://banthe247.com/upload/files/mua-the-cao-vinaphone-chiet-khau-3%2C5-%20cac-menh-gia.jpg");
        quangCao.add("https://media.vietteltelecom.vn/upload/ckfinder/images/banner_gui-01.jpg");
        for(int i = 0; i<quangCao.size();i++){
            ImageView imageView= new ImageView(getContext());
            Picasso.with(getContext()).load(quangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            vFlipper2.addView(imageView);
        }
        vFlipper2.setFlipInterval(4000);
        vFlipper2.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in);
        Animation animation_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out);
        vFlipper2.setInAnimation(animation_in);
        vFlipper2.setOutAnimation(animation_out);
    }

    private void getDichVu(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int id =0;
                    String tenDV ="";
                    String tenHang ="";
                    Integer gia =0;
                    String anh ="";
                    for (int i = 0; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenDV = jsonObject.getString("tendichvu");
                            tenHang = jsonObject.getString("tenhang");
                            gia = jsonObject.getInt("gia");
                            anh = jsonObject.getString("hinhanhdichvu");
                            listDichVu.add(new NapTheVaDichVuHome(id,tenDV,tenHang,gia,anh));
                            dichVuAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getTimKiem(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int id =0;
                    String ten ="";
                    String anh ="";
                    for (int i = 0; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            ten= jsonObject.getString("tenloaisp");
                            anh = jsonObject.getString("hinhanhloaisp");
                            listTimKiem.add(new TimKiemHangDauHome(id,ten,anh));
                            timkiemAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getDanhMuc(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int id = 0;
                    String Ten = "";
                    String Anh = "";
                    for (int i =0 ; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Ten = jsonObject.getString("tenloaisp");
                            Anh = jsonObject.getString("hinhanhloaisp");
                            danhMucArrayList.add(new DanhMucHome(id,Ten,Anh));
                            danhMucAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void viewFlipper() {
        ArrayList<String> quangCao = new ArrayList<>();
        quangCao.add("https://images.prismic.io/frameworkmarketplace/5dc5fc06-aec5-4f28-a924-0230aa91a360_Pre-Marketplace+-+image_02.jpg?auto=compress,format");
        quangCao.add("https://static.vecteezy.com/system/resources/thumbnails/001/338/250/small/black-friday-sale-banner-free-vector.jpg");
        quangCao.add("https://sonypro.vn/wp-content/uploads/2021/04/Dien-thoai-gia-re-1.jpg");
        for(int i = 0; i<quangCao.size();i++){
            ImageView imageView= new ImageView(getContext());
            Picasso.with(getContext()).load(quangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            vFlipper.addView(imageView);
        }
        vFlipper.setFlipInterval(5000);
        vFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in);
        Animation animation_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out);
        vFlipper.setInAnimation(animation_in);
        vFlipper.setOutAnimation(animation_out);
    }

}