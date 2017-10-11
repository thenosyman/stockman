package com.thenosyman.stockman.stockman.network;

import android.support.annotation.Nullable;

import com.thenosyman.stockman.stockman.model.SecurityModel;
import com.thenosyman.stockman.stockman.model.SecurityTimeSeriesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ming on 10/7/2017.
 */

public interface AlphavantageService {

    String INTERVAL_1MIN = "1min";
    String INTERVAL_15MIN = "15min";

    String QUERY_TIME_SERIES_DAILY = "TIME_SERIES_DAILY";
    @GET("/query")
    Call<SecurityTimeSeriesModel> getTimeSeries(@Query("function") String function,
                                                      @Query("symbol") String symbol,
                                                      @Query("interval") String interval,
                                                      @Query("outputSize") @Nullable String outputSize,
                                                      @Query("dataType") @Nullable String dataType,
                                                      @Query("apikey") String apiKey);

}
