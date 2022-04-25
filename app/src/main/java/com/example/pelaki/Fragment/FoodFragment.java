package com.example.pelaki.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pelaki.Adapter.Layout1Adapter;
import com.example.pelaki.Adapter.NapTheVaDichVuAdapter;
import com.example.pelaki.Model.DanhMucHome;
import com.example.pelaki.Model.Layout1Food;
import com.example.pelaki.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    String UrlLayout1 = "http://loyalist002-001-site1.gtempurl.com/jsonfood.php";
    RecyclerView recyLayout1;
    ArrayList<Layout1Food> listLayout1;
    Layout1Adapter layout1Adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food,container,false);

        recyLayout1= view.findViewById(R.id.recyLayout1);
        listLayout1 = new ArrayList<>();
        layout1Adapter = new Layout1Adapter(getContext(),listLayout1);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyLayout1.setLayoutManager(layout);
        recyLayout1.setAdapter(layout1Adapter);

        getLayout1(UrlLayout1);
        return view;
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
}
