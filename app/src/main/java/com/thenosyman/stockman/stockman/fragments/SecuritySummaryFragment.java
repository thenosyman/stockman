package com.thenosyman.stockman.stockman.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thenosyman.stockman.stockman.R;
import com.thenosyman.stockman.stockman.model.SecurityDeserializer;
import com.thenosyman.stockman.stockman.model.SecurityModel;
import com.thenosyman.stockman.stockman.model.SecurityTimeSeriesDeserializer;
import com.thenosyman.stockman.stockman.model.SecurityTimeSeriesModel;
import com.thenosyman.stockman.stockman.network.AlphavantageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SecuritySummaryFragment extends Fragment {

    private static final String TAG = "SecuritySummaryFragment";
    private AlphavantageService mAlphavantageService;
    private LineChart chart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_security_summary, container, false);
        if(chart == null) {
            chart = view.findViewById(R.id.chart);
            createChart();
        }
        return view;
    }

    private void setChartData(SecurityTimeSeriesModel securityTimeSeriesModel) {

        HashMap<Calendar, SecurityModel> map = securityTimeSeriesModel.getTimeSeries();
        SortedSet<Calendar> dates = new TreeSet<>(map.keySet());

        int i=0;
        List<Entry> entries = new ArrayList<>();
        final ArrayList<String> xLabel = new ArrayList<>();
        for (Calendar date : dates) {

            SecurityModel security = securityTimeSeriesModel.getTimeSeries().get(date);
            // turn your data into Entry objects
            entries.add(new Entry(i++, security.getHigh()));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); // 2017-10-10 15:59:0
            String strDate = sdf.format(date.getTime());
            xLabel.add(strDate);
        }


        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(0xFFFF);
        dataSet.setValueTextColor(0xFFFF); // styling, ...

        dataSet.setColor(Color.RED);
        dataSet.setDrawValues(false);
        dataSet.setDrawCircles(false);


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int)value);
            }
        });
        xAxis.setLabelRotationAngle(90);

        YAxis yAxis = chart.getAxisRight();
        yAxis.setDrawLabels(false);


        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        Description description = new Description();
        description.setText("Open value");
        chart.setDescription(description);
        chart.invalidate(); // refresh
    }

    public static Fragment newInstance(){
        return new SecuritySummaryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createChart();
    }

    @Override
    public void onResume() {
        super.onResume();
        createChart();
    }

    private void createChart(){
        createAlphavantageService();

        mAlphavantageService.getTimeSeries(AlphavantageService.QUERY_TIME_SERIES_DAILY,
                "IBM",
                AlphavantageService.INTERVAL_1MIN,
                null,
                null,
                "N9ZCSY4V80GU1L5B"
        ).enqueue(dataCallback);


    }

    private void createAlphavantageService(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(SecurityTimeSeriesModel.class,
                        new SecurityTimeSeriesDeserializer())
                .registerTypeAdapter(SecurityModel.class, new SecurityDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.alphavantage.co")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mAlphavantageService = retrofit.create(AlphavantageService.class);
    }

    Callback<SecurityTimeSeriesModel> dataCallback= new Callback<SecurityTimeSeriesModel>(){

        @Override
        public void onResponse(Call<SecurityTimeSeriesModel> call, Response<SecurityTimeSeriesModel> response) {
            if(response.isSuccessful()){
                Log.d(TAG, "response successful: "+response.body().toString());
                setChartData(response.body());
            }else{
                Log.d(TAG, "response not successful");
            }
        }

        @Override
        public void onFailure(Call<SecurityTimeSeriesModel> call, Throwable t) {
            Log.d(TAG, "response failed");
        }
    };


}
