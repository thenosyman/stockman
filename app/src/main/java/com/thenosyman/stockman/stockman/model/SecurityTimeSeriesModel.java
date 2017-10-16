package com.thenosyman.stockman.stockman.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * Created by Ming on 10/9/2017.
 */

public abstract class SecurityTimeSeriesModel {

    public static final String OUTPUT_SIZE_COMPACT = "compact";
    public static final String OUTPUT_SIZE_FULL = "";

    private String mInformation;
    private String mSymbol;
    private Date mLastRefreshedDate;
    private String mInterval;
    private TimeZone mTimeZone;
    private HashMap<Calendar, SecurityModel> mTimeSeries;

    public String getInformation() {
        return mInformation;
    }

    public void setInformation(String information) {
        mInformation = information;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public Date getLastRefreshedDate() {
        return mLastRefreshedDate;
    }

    public void setLastRefreshedDate(Date lastRefreshedDate) {
        mLastRefreshedDate = lastRefreshedDate;
    }

    public String getInterval() {
        return mInterval;
    }

    public void setInterval(String interval) {
        mInterval = interval;
    }

    public TimeZone getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        mTimeZone = timeZone;
    }

    public HashMap<Calendar, SecurityModel> getTimeSeries() {
        if(mTimeSeries == null)
            mTimeSeries = new HashMap<>();
        return mTimeSeries;
    }

    public void setTimeSeries(HashMap<Calendar, SecurityModel> timeSeries) {
        mTimeSeries = timeSeries;
    }

    protected abstract String getType();

    public String getTimeSeriesDescription(){
        return "Time Series ("+getInterval()+")";
    };
}