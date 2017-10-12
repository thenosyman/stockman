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

    @Override
    protected Fragment createFragment() {
        return SecuritySummaryFragment.newInstance();
    }

    @Override
    protected int getFragmentId() {
        return R.id.security_summary;
    }

}
