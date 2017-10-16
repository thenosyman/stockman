package com.thenosyman.stockman.stockman.model;

/**
 * Created by Ming on 10/15/2017.
 */

public class WeeklyTimeSeries extends SecurityTimeSeriesModel {

    public static final String TYPE = "TIME_SERIES_MONTHLY";


    @Override
    protected String getType() {
        return WeeklyTimeSeries.TYPE;
    }
}
