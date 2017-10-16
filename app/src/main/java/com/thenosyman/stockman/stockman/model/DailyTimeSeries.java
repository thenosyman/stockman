package com.thenosyman.stockman.stockman.model;

/**
 * Created by Ming on 10/12/2017.
 */

public class DailyTimeSeries extends SecurityTimeSeriesModel{

    public static final String TYPE = "TIME_SERIES_DAILY";

    @Override
    protected String getType(){
        return DailyTimeSeries.TYPE;
    }

}
