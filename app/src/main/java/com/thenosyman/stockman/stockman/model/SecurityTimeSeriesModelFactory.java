package com.thenosyman.stockman.stockman.model;

/**
 * Created by Ming on 10/13/2017.
 */

public class SecurityTimeSeriesModelFactory {

    private final static String DAILY_TIME_SERIES_KEY = "Daily";

    public static SecurityTimeSeriesModel createSecurityTimeSeriesModel(String timeSeriesDesc){

        timeSeriesDesc = timeSeriesDesc.toLowerCase();
        String intradayKey = "intraday";
        String dailyKey = "daily";
        String weeklyKey = "weekly";
        String monthlyKey = "monthly";

        if(timeSeriesDesc.contains(intradayKey)){
            return new IntradayTimeSeries();
        }else if(timeSeriesDesc.contains(dailyKey)){
            return new DailyTimeSeries();
        }else if(timeSeriesDesc.contains(weeklyKey)){
            return new WeeklyTimeSeries();
        }else if(timeSeriesDesc.contains(monthlyKey)){
            return new MonthlyTimeSeries();
        }
        throw new IllegalArgumentException("Error: bad time series description:"+timeSeriesDesc);
    }
}
