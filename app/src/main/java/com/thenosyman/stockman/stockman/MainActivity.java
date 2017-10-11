package com.thenosyman.stockman.stockman;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thenosyman.stockman.stockman.activities.SingleFragment;
import com.thenosyman.stockman.stockman.fragments.SecuritySummaryFragment;
import com.thenosyman.stockman.stockman.model.SecurityDeserializer;
import com.thenosyman.stockman.stockman.model.SecurityModel;
import com.thenosyman.stockman.stockman.model.SecurityTimeSeriesDeserializer;
import com.thenosyman.stockman.stockman.model.SecurityTimeSeriesModel;
import com.thenosyman.stockman.stockman.network.AlphavantageAPI;
import com.thenosyman.stockman.stockman.network.AlphavantageService;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends SingleFragment {

    private final static String TAG = "MainActivity";
    private AlphavantageService mAlphavantageService;

    @Override
    protected Fragment createFragment() {
        return SecuritySummaryFragment.newInstance();
    }

    @Override
    protected int getFragmentId() {
        return R.id.security_summary;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
