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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AggregatesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_aggregates, container, false);
        final Button fetchButton = view.findViewById(R.id.fetch_button);
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

        fetchButton.setOnClickListener(new View.OnClickListener() {
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
                StringRequest stringRequest = new StringRequest(Request.Method.GET, completeUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent aggregatesIntent = new Intent(getActivity(), AggregatesActivity.class);
                            Bundle responseData = new Bundle();
                            responseData.putString("responseData", response);
                            aggregatesIntent.putExtras(responseData);
                            startActivity(aggregatesIntent);
                            //getActivity().finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            desc.setText(error.toString());
                        }
                });
                queue.add(stringRequest);
            }
        });

        return view;
    }
}
