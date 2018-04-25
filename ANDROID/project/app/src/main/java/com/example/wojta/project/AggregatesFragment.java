package com.example.wojta.project;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

        return view;
    }
}
