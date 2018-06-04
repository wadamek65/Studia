package com.example.wojta.project;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class HistogramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregates);

        GraphView graph = findViewById(R.id.graph_histogram);

        Bundle responseData = getIntent().getExtras();
        if (responseData != null) {
            double[] arrayX = responseData.getDoubleArray("x_array");
            double[] arrayY = responseData.getDoubleArray("y_array");

            DataPoint[] histogramData = new DataPoint[arrayX.length];
            for (int i = 0; i < arrayX.length; i++) {
                histogramData[i] = new DataPoint(arrayY[i], arrayX[i]);
            }

            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(histogramData);
            graph.addSeries(series);

            series.setDataWidth(1);
            series.setSpacing(0);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(series.getLowestValueX() - series.getLowestValueX()/20);
            graph.getViewport().setMaxX(series.getHighestValueX() + series.getLowestValueX()/20);

            series.setTitle("Data");
            graph.getLegendRenderer().setVisible(true);
            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        }
    }
}
