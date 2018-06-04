package com.example.wojta.project;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class AggregatesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_aggregates, container, false);
        final Button aggregatesButton = view.findViewById(R.id.aggregates_button);
        final Button histogramButton = view.findViewById(R.id.histogram_button);
        final Button fillButton = view.findViewById(R.id.fill_button);

        final EditText dateStartInput = view.findViewById(R.id.date_start_input);
        final EditText dateEndInput = view.findViewById(R.id.date_end_input);
        final EditText cordIdInput = view.findViewById(R.id.cord_id_input);
        final EditText acronymInput = view.findViewById(R.id.acronym_input);
        final EditText kpiInput = view.findViewById(R.id.kpi_input);
        final EditText binsInput = view.findViewById(R.id.bins_input);

        final TextView desc = view.findViewById(R.id.aggregates_footer);

        final RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        fillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateStartInput.setText("2016-06-01");
                dateEndInput.setText("2018-01-01");
                cordIdInput.setText("Skuntank");
                acronymInput.setText("dilfihess");
                kpiInput.setText("SGSN_2012");
            }
        });
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baseUrl = "http://www.healthiness-of-data.ovh/api/clusters/aggregates/";
                String dateStart = dateStartInput.getText().toString();
                String dateEnd = dateEndInput.getText().toString();
                String cordID = cordIdInput.getText().toString();
                String acronym = acronymInput.getText().toString();
                String kpi = kpiInput.getText().toString();
                String bins = binsInput.getText().toString();

                String completeUrl = baseUrl + cordID + '/' + acronym + "?date_start=" + dateStart + "&date_end=" + dateEnd + "&kpi_basename=" + kpi + "&bins=" + bins;
                desc.setText("Fetching data...");
                JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, completeUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent aggregatesIntent = new Intent(getActivity(), AggregatesActivity.class);
                                Bundle responseData = new Bundle();
                                try {

                                    Double minValue = response.getDouble("min_val");
                                    Double maxValue = response.getDouble("max_val");
                                    Double mean = response.getDouble("mean");
                                    Double stdDeviation = response.getDouble("std_deviation");
                                    JSONArray array = response.getJSONArray("distribution");
                                    JSONArray xarray = (JSONArray) array.get(0);
                                    JSONArray yarray = (JSONArray) array.get(1);

                                    double[] arrayX = new double[xarray.length()];
                                    double[] arrayY = new double[yarray.length()];

                                    for (int i = 0; i < xarray.length(); i++){
                                        arrayX[i] = xarray.getDouble(i);
                                    }

                                    for (int i = 0; i < yarray.length()-1; i++){
                                        arrayY[i] = yarray.getDouble(i);
                                    }

                                    responseData.putDoubleArray("x_array", arrayX);
                                    responseData.putDoubleArray("y_array", arrayY);

                                    responseData.putDouble("min_val", minValue);
                                    responseData.putDouble("max_val", maxValue);
                                    responseData.putDouble("mean", mean);
                                    responseData.putDouble("std_deviation", stdDeviation);

                                    aggregatesIntent.putExtras(responseData);
                                    desc.setText("");
                                    startActivity(aggregatesIntent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                queue.add(json);
            }
        };
        View.OnClickListener histogramListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baseUrl = "http://www.healthiness-of-data.ovh/api/clusters/aggregates/";
                String dateStart = dateStartInput.getText().toString();
                String dateEnd = dateEndInput.getText().toString();
                String cordID = cordIdInput.getText().toString();
                String acronym = acronymInput.getText().toString();
                String kpi = kpiInput.getText().toString();
                String bins = binsInput.getText().toString();

                String completeUrl = baseUrl + cordID + '/' + acronym + "?date_start=" + dateStart + "&date_end=" + dateEnd + "&kpi_basename=" + kpi + "&bins=" + bins;
                desc.setText("Fetching data...");
                JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, completeUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent aggregatesIntent = new Intent(getActivity(), HistogramActivity.class);
                                Bundle responseData = new Bundle();
                                try {

                                    Double minValue = response.getDouble("min_val");
                                    Double maxValue = response.getDouble("max_val");
                                    Double mean = response.getDouble("mean");
                                    Double stdDeviation = response.getDouble("std_deviation");
                                    JSONArray array = response.getJSONArray("distribution");
                                    JSONArray xarray = (JSONArray) array.get(0);
                                    JSONArray yarray = (JSONArray) array.get(1);

                                    double[] arrayX = new double[xarray.length()];
                                    double[] arrayY = new double[yarray.length()];

                                    for (int i = 0; i < xarray.length(); i++){
                                        arrayX[i] = xarray.getDouble(i);
                                    }

                                    for (int i = 0; i < yarray.length()-1; i++){
                                        arrayY[i] = yarray.getDouble(i);
                                    }

                                    responseData.putDoubleArray("x_array", arrayX);
                                    responseData.putDoubleArray("y_array", arrayY);

                                    responseData.putDouble("min_val", minValue);
                                    responseData.putDouble("max_val", maxValue);
                                    responseData.putDouble("mean", mean);
                                    responseData.putDouble("std_deviation", stdDeviation);

                                    aggregatesIntent.putExtras(responseData);
                                    desc.setText("");
                                    startActivity(aggregatesIntent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                queue.add(json);
            }
        };
        aggregatesButton.setOnClickListener(clickListener);
        histogramButton.setOnClickListener(histogramListener);
        return view;
    }
}
