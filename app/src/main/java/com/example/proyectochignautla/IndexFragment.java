package com.example.proyectochignautla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectochignautla.Class.Reports;
import com.example.proyectochignautla.adapters.ReportsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class IndexFragment extends Fragment implements SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    ArrayList<Reports> ListaReportes;
    ReportsAdapter reportsAdapter;
    SearchView svSearch;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_index, container, false);
        ListaReportes = new ArrayList<>();
        recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);
        svSearch = (SearchView) vista.findViewById(R.id.svSearch);

        Data();
        initListener();
        return vista;
    }

    private void initListener(){
        svSearch.setOnQueryTextListener(this);
    }

    public void Data() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getActivity());
        String url = "https://hostchignautla.000webhostapp.com/getReport.php";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Reports reports = null;
                JSONArray jsonArray = response.optJSONArray("reports");
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        reports = new Reports();
                        JSONObject jsonObject = null;
                        jsonObject = jsonArray.getJSONObject(i);
                        reports.setName(jsonObject.optString("name") + " ");
                        reports.setFatherlastname(jsonObject.optString("fatherlastname") + " ");
                        reports.setMotherlatsname(jsonObject.optString("motherlastname"));
                        reports.setPhonenumber(jsonObject.optString("phonenumber"));
                        reports.setAge(jsonObject.optString("age"));
                        reports.setColony(jsonObject.optString("colony"));
                        reports.setStreet1(jsonObject.optString("street1"));
                        reports.setStreet2(jsonObject.optString("street2"));
                        reports.setArea(jsonObject.optString("area"));
                        reports.setProblem(jsonObject.optString("problem"));
                        reports.setDescription(jsonObject.optString("description"));
                        reports.setCreated_at(jsonObject.optString("created_at"));
                        reports.setStatus(jsonObject.optString("status"));

                        ListaReportes.add(reports);
                    }
                    reportsAdapter = new ReportsAdapter(ListaReportes);
                    recyclerView.setAdapter(reportsAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        reportsAdapter.filter(newText);
        return false;
    }
}













