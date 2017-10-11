package com.thenosyman.stockman.stockman.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * Created by Ming on 10/9/2017.
 */

public class SecurityTimeSeriesModel {

    private String mInformation;
    private String mSymbol;
    private Date mLastRefreshedDate;
    private long mIntervalMs;
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

    public long getIntervalMs() {
        return mIntervalMs;
    }

    public void setIntervalMs(long intervalMs) {
        mIntervalMs = intervalMs;
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
}