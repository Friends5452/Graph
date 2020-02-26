package com.example.multiplegraphs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    Viewport viewport;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment2, container, false);
        GraphView graphView=view.findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        graphView.addSeries(series);
        viewport = graphView.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(5);
        viewport.setXAxisBoundsManual(true);
        viewport.scrollToEnd();
        viewport.setScrollable(true);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (int i = 0; i < 100; i++) {


                    addEntry();
                    try {
                        Thread.sleep(90);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return view;
    }
    private void addEntry() {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        //%5 taki value 5 ke andar rahe ok type kar do
        series.appendData(new DataPoint(lastX++,RANDOM.nextInt(5)), true, 200);
    }

}
