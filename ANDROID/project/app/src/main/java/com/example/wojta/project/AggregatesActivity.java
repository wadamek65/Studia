package com.example.wojta.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AggregatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregates);

        final TextView resp = findViewById(R.id.request_response);

        Bundle responseData = getIntent().getExtras();
        if (responseData != null) {
            String responseString = responseData.getString("responseData");
            resp.setText(responseString);
        }
    }
}
