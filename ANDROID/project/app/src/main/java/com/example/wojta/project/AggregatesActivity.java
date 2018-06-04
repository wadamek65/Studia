package com.example.wojta.project;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Set;

public class AggregatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregates);

        GraphView graph = findViewById(R.id.graph_histogram);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);

        Bundle responseData = getIntent().getExtras();
        if (responseData != null) {

            PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(new DataPoint[] {
                    new DataPoint(1, responseData.getDouble("mean")),
            });

            PointsGraphSeries<DataPoint> series3 = new PointsGraphSeries<>(new DataPoint[] {
                    new DataPoint(2, responseData.getDouble("std_deviation")),
            });

            PointsGraphSeries<DataPoint> series4 = new PointsGraphSeries<>(new DataPoint[] {
                    new DataPoint(3, responseData.getDouble("min_val")),
            });

            PointsGraphSeries<DataPoint> series5 = new PointsGraphSeries<>(new DataPoint[] {
                    new DataPoint(4, responseData.getDouble("max_val"))
            });

            graph.addSeries(series2);
            graph.addSeries(series3);
            graph.addSeries(series4);
            graph.addSeries(series5);
            series2.setColor(Color.GREEN);
            series2.setShape(PointsGraphSeries.Shape.TRIANGLE);
            series3.setColor(Color.RED);
            series3.setShape(PointsGraphSeries.Shape.TRIANGLE);
            series4.setColor(Color.YELLOW);
            series4.setShape(PointsGraphSeries.Shape.TRIANGLE);
            series5.setColor(Color.BLACK);
            series5.setShape(PointsGraphSeries.Shape.TRIANGLE);
            series2.setTitle("Mean");
            series3.setTitle("Standard Deviation");
            series4.setTitle("Minimum");
            series5.setTitle("Maximum");
            graph.getLegendRenderer().setVisible(true);
            graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        }
    }
}
