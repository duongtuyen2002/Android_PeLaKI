package com.example.pelaki.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.pelaki.Adapter.FOOD.Layout1Adapter;
import com.example.pelaki.Adapter.FOOD.ThucAnAdapter;
import com.example.pelaki.Fragment.Activity.Activity_GioHang_Food;
import com.example.pelaki.Model.FOOD.Layout1Food;
import com.example.pelaki.Model.FOOD.ThucAn;
import com.example.pelaki.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    String UrlLayout1 = "http://loyalist002-001-site1.gtempurl.com/jsonfood.php";
    String UrlThucAn = "http://loyalist002-001-site1.gtempurl.com/jsonthucan.php";
    RecyclerView recyLayout1,recyThucan;
    ArrayList<Layout1Food> listLayout1;
    Layout1Adapter layout1Adapter;

    ArrayList<ThucAn> ListthucAn;
    ThucAnAdapter thucAnAdapter;
    ViewFlipper vFlipperFood;

    EditText search;
    TextView btnBack, btnGio;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food,container,false);
        vFlipperFood = view.findViewById(R.id.vFliper_food);
        search = view.findViewById(R.id.edit_timkiemfood);
        btnBack = view.findViewById(R.id.tv_back_food);
        btnGio = view.findViewById(R.id.tv_donhang);
        //Layout1
        recyLayout1= view.findViewById(R.id.recyLayout1);
        listLayout1 = new ArrayList<>();
        layout1Adapter = new Layout1Adapter(getContext(),listLayout1);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyLayout1.setLayoutManager(layout);
        recyLayout1.setAdapter(layout1Adapter);

        //Thuc An
        recyThucan = view.findViewById(R.id.recy_thucan);
        ListthucAn = new ArrayList<>();
        thucAnAdapter = new ThucAnAdapter(getContext(), ListthucAn);
        GridLayoutManager layoutThucAn = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyThucan.setLayoutManager(layoutThucAn);
        recyThucan.setAdapter(thucAnAdapter);
        viewFlipper();
        getLayout1(UrlLayout1);
        getThucAn(UrlThucAn);

       btnGio.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getContext(), Activity_GioHang_Food.class);
               startActivity(intent);
           }
       });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {filter(editable.toString());

            }
        });
        return view;
    }

    private void filter(String text) {
        ArrayList<ThucAn> filterList = new ArrayList<>();
        for(ThucAn TC: ListthucAn){
            if (TC.getTen().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(TC);
            }
        }
        thucAnAdapter.filterSP(filterList);
    }

    private void getThucAn(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int id = 0;
                    int gia = 0;
                    String Ten = "";
                    String Anh = "";
                    for (int i =0 ; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Ten = jsonObject.getString("ten");
                            gia = jsonObject.getInt("gia");
                            Anh = jsonObject.getString("hinhanh");
                            ListthucAn.add(new ThucAn(id,Ten,gia,Anh));
                            thucAnAdapter.notifyDataSetChanged();
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

    private void getLayout1(String url) {
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
                            listLayout1.add(new Layout1Food(id,Ten,Anh));
                            layout1Adapter.notifyDataSetChanged();
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
            vFlipperFood.addView(imageView);
        }
        vFlipperFood.setFlipInterval(3000);
        vFlipperFood.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in);
        Animation animation_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out);
        vFlipperFood.setInAnimation(animation_in);
        vFlipperFood.setOutAnimation(animation_out);
    }
}
