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
import com.example.pelaki.Adapter.Noti.NotiAdapter;
import com.example.pelaki.Model.Noti.Noti;
import com.example.pelaki.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Noti> notiArrayList;
    NotiAdapter notiAdapter;
    String URL = "http://loyalist002-001-site1.gtempurl.com/jsonthogbao.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noti, container, false);
        //Anh xa
        recyclerView = view.findViewById(R.id.recy_noti);
        notiArrayList = new ArrayList<>();
        notiAdapter = new NotiAdapter(getContext(),notiArrayList);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(notiAdapter);
        getThongbao(URL);
        return view;
    }

    private void getThongbao(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int id =0;
                    String tentb ="";
                    String noidung ="";
                    String anh ="";
                    for (int i = 0; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tentb = jsonObject.getString("tentb");
                            noidung = jsonObject.getString("noidungtb");
                            anh = jsonObject.getString("hinhanhtb");
                            notiArrayList.add(new Noti(id,tentb,noidung,anh));
                            notiAdapter.notifyDataSetChanged();
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
