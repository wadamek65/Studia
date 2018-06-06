package com.example.wojta.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class DataFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        final TextView desc = view.findViewById(R.id.data);
        final Button cords = view.findViewById(R.id.acronyms);
        final Button kpis = view.findViewById(R.id.kpis);
        final RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        cords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc.setText("Fetching data...");
                String completeUrl = "http://www.healthiness-of-data.ovh/api/fetch_cord_acronym_set";
                JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, completeUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                desc.setText("");
                                try {
                                    Iterator<?> keys = response.keys();

                                    while (keys.hasNext()) {
                                        String key = (String)keys.next();
                                        desc.setText(desc.getText()+key+": "+response.get(key).toString().replace("\"", "")
                                                .replace("[", "")
                                                .replace("]", "")
                                                .replace(",", ", ")
                                                +'\n');
                                    }
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        desc.setText("Something went wrong, check if entered parameters are correct");
                    }
                });
                RetryPolicy policy = new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                json.setRetryPolicy(policy);
                queue.add(json);
            }
        });

        kpis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc.setText("Fetching data...");
                String completeUrl = "http://www.healthiness-of-data.ovh/api/fetch_kpi_basenames";
                JsonArrayRequest json = new JsonArrayRequest(Request.Method.GET, completeUrl, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                desc.setText("");
                                    desc.setText(response.toString().replace("\"", "").replace(",", ", "));

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        desc.setText("Something went wrong, check if entered parameters are correct");
                    }
                });
                RetryPolicy policy = new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                json.setRetryPolicy(policy);
                queue.add(json);
            }
        });

        return view;
    }
}
