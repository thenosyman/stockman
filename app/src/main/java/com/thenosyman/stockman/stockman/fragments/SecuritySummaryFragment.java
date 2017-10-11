package com.thenosyman.stockman.stockman.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thenosyman.stockman.stockman.R;
import com.thenosyman.stockman.stockman.network.AlphavantageService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SecuritySummaryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_security_summary, container, false);
    }

    public static Fragment newInstance(){
        return new SecuritySummaryFragment();
    }

    private void createAlphavantageService(){
        //how does  model class being used here?

        Gson gson = new GsonBuilder()
                .setDateFormat("mm/dd/yyyy")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.alphavantage.co")

                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AlphavantageService alphavantageService = retrofit.create(AlphavantageService.class);
        alphavantageService.getTimeSeries("","","","","","");
    }
}
