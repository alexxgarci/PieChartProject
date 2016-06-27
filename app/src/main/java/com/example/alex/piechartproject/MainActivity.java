package com.example.alex.piechartproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alex.piechart.PieChart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieChart pieChart = (PieChart)findViewById(R.id.piechart);

        pieChart.setmNumWedges(6);


        float[] percent = {25,20,10,15,10,20};
        int[] colors = {Color.BLUE,Color.BLACK,Color.CYAN,Color.GREEN,Color.RED,Color.GRAY};
        String[] names = {"color1","color2","color3","color4","color5","color6"};

        for (int i=0; i < pieChart.getmNumWedges();i++){
            pieChart.setmPercentatge(percent[i]);
            pieChart.setmWedgeColor(colors[i]);
            pieChart.setmWedgeName(names[i]);
        }

    }
}
