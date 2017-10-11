package com.thenosyman.stockman.stockman.network;

import com.thenosyman.stockman.stockman.model.SecurityModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Ming on 10/7/2017.
 */

public class AlphavantageAPI {

    private static AlphavantageAPI mAlphavantage;

    public static AlphavantageAPI getApi(){
        if(mAlphavantage != null)
            mAlphavantage = new AlphavantageAPI();
        return mAlphavantage;
    }

    private AlphavantageAPI() {

        /*
         * authentication with OkHttp interceptors
         * below will avoid the need to add authorization header for each request
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().header("Authorization", "authorization_token");
                Request newRequest = build er.build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(okHttpClient)
                .build();
        **/
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://www.alphavantage.co/")
                .build();
        AlphavantageService alphavantageService = retrofit.create(AlphavantageService.class);
        //Call<SecurityModel> call = alphavantageService.getTimeSeries();
    }
}
