package com.example.pelaki.Fragment.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pelaki.Adapter.Activity.DienThoaiAdapter;
import com.example.pelaki.Model.Acivity.DienThoai;
import com.example.pelaki.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_DienThoai extends FragmentActivity {
    String URL = "http://loyalist002-001-site1.gtempurl.com/jsondienthoai.php";
    RecyclerView recyclerView;
    ArrayList<DienThoai> list;
    DienThoaiAdapter adapter;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dienthoai);
        recyclerView = findViewById(R.id.recy_dt);
        list = new ArrayList<>();
        adapter = new DienThoaiAdapter(getApplicationContext(),list);
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
        search = findViewById(R.id.edit_timkiem);
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
        getDienThoai(URL);
    }

    private void getDienThoai(String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
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
                            Ten = jsonObject.getString("tensp");
                            gia = jsonObject.getInt("giasp");
                            Anh = jsonObject.getString("hinhanhsp");
                            list.add(new DienThoai(id,gia,Ten,Anh));
                            adapter.notifyDataSetChanged();
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

    public void filter(String text) {
        ArrayList<DienThoai> filterList = new ArrayList<>();
        for(DienThoai TC: list){
            if (TC.getTen().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(TC);
            }
        }
       adapter.filterSP(filterList);
    }
}