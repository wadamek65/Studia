package com.example.wojta.project;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import java.util.Arrays;

public class OutliersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregates);

        GraphView graph = findViewById(R.id.graph_histogram);


        Bundle responseData = getIntent().getExtras();
        if (responseData != null) {
            double[] valuesArray = responseData.getDoubleArray("valuesArray");
            double[] outliersArray = responseData.getDoubleArray("outliersArray");

//            Arrays.sort(valuesArray);
            Arrays.sort(outliersArray);

            DataPoint[] valuesData = new DataPoint[valuesArray.length];
            DataPoint[] outliersData = new DataPoint[outliersArray.length];

            for (int i = 0; i < valuesArray.length; i++) {
                valuesData[i] = new DataPoint(i, valuesArray[i]);
            }
            int x = 0;
            for (double outlier : outliersArray) {
                outliersData[x++] = new DataPoint(outlier+1, valuesArray[(int)outlier]);
            }

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(valuesData);
            graph.addSeries(series);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(series.getLowestValueX() - series.getLowestValueX()/20);
            graph.getViewport().setMaxX(series.getHighestValueX() + series.getLowestValueX()/20);

            PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(outliersData);

            graph.addSeries(series2);

            series2.setColor(Color.GREEN);
            series2.setShape(PointsGraphSeries.Shape.TRIANGLE);
            series.setTitle("Data");
            series2.setTitle("Outliers");
            graph.getLegendRenderer().setVisible(true);
            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        }
    }
}
