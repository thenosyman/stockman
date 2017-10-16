package com.thenosyman.stockman.stockman.model;

/**
 * Created by Ming on 10/15/2017.
 */

public class IntradayTimeSeries extends SecurityTimeSeriesModel{

    public static final String TYPE = "TIME_SERIES_INTRADAY";
    public static final String INTERVAL_1MIN = "1min";
    public static final String INTERVAL_5MIN = "5min";
    public static final String INTERVAL_15MIN = "15min";
    public static final String INTERVAL_30MIN = "30min";
    public static final String INTERVAL_60MIN = "60MIN";

    @Override
    protected String getType() {
        return IntradayTimeSeries.TYPE;
    }
}
