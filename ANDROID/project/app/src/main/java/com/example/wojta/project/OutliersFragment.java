package com.example.wojta.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OutliersFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_outliers, container, false);
        final Button fetchButton = view.findViewById(R.id.fetch_button);
        final Button fillButton = view.findViewById(R.id.fill_button);

        final EditText dateStartInput = view.findViewById(R.id.date_start_input);
        final EditText dateEndInput = view.findViewById(R.id.date_end_input);
        final EditText cordIdInput = view.findViewById(R.id.cord_id_input);
        final EditText acronymInput = view.findViewById(R.id.acronym_input);
        final EditText kpiInput = view.findViewById(R.id.kpi_input);
        final EditText thresholdInput = view.findViewById(R.id.threshold_input);

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

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baseUrl = "http://www.healthiness-of-data.ovh/api/outliers/";
                String dateStart = dateStartInput.getText().toString();
                String dateEnd = dateEndInput.getText().toString();
                String cordID = cordIdInput.getText().toString();
                String acronym = acronymInput.getText().toString();
                String kpi = kpiInput.getText().toString();
                String bins = thresholdInput.getText().toString();

                String completeUrl = baseUrl + cordID + '/' + acronym + "?date_start=" + dateStart + "&date_end=" + dateEnd + "&kpi_basename=" + kpi + "&threshold=" + bins;
                desc.setText("Fetching data...");
                JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, completeUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent outliersIntent = new Intent(getActivity(), OutliersActivity.class);
                                Bundle responseData = new Bundle();
                                try {

                                    JSONArray array = response.getJSONArray("values");
                                    JSONArray outlierArray = response.getJSONArray("outliers");

                                    double[] valuesArray = new double[array.length()];
                                    double[] outliersArray = new double[outlierArray.length()];

                                    for (int i = 0; i < array.length(); i++){
                                        valuesArray[i] = array.getDouble(i);
                                    }

                                    for (int i = 0; i < outlierArray.length()-1; i++){
                                        outliersArray[i] = outlierArray.getDouble(i);
                                    }

                                    responseData.putDoubleArray("valuesArray", valuesArray);
                                    responseData.putDoubleArray("outliersArray", outliersArray);

                                    outliersIntent.putExtras(responseData);
                                    desc.setText("");
                                    startActivity(outliersIntent);
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
        });
        return view;
    }
}
