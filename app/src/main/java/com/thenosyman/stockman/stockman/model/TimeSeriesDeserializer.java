package com.thenosyman.stockman.stockman.model;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Ming on 10/15/2017.
 */

public class TimeSeriesDeserializer implements JsonDeserializer<SecurityTimeSeriesModel> {

    private static final String TAG = "TimeSeriesDeserializer";

    @Override
    public SecurityTimeSeriesModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement metaJson = json.getAsJsonObject().get("Meta Data");

        String description = metaJson.getAsJsonObject().get("1. Information").getAsString();
        SecurityTimeSeriesModel securityTimeSeriesModel = SecurityTimeSeriesModelFactory.createSecurityTimeSeriesModel(description);

        String timeSeriesKey = getTimeSeriesKey(metaJson);
        JsonElement timeSeriesMap = json.getAsJsonObject().get(timeSeriesKey);

        for(Map.Entry<String, JsonElement> securityData : timeSeriesMap.getAsJsonObject().entrySet()){
            String strDate = securityData.getKey();
            Log.d(TAG, strDate);
            SecurityModel securityModel = context.deserialize(securityData.getValue(), SecurityModel.class);

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); // 2017-10-10 15:59:0
            try {
                cal.setTime(sdf.parse(strDate));
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }

            securityTimeSeriesModel.getTimeSeries().put(cal, securityModel);
        }

        return securityTimeSeriesModel;
    }

    private String getTimeSeriesKey(JsonElement metaJson){

        String informationKey = metaJson.getAsJsonObject()
                .get("1. Information").getAsString();
        informationKey = informationKey.toLowerCase();
        String intradayKey = "intraday";
        String dailyKey = "daily";
        String weeklyKey = "weekly";
        String monthlyKey = "monthly";

        if(informationKey.contains(intradayKey)){
            String interval = metaJson.getAsJsonObject()
                    .get("4. Interval").getAsString();
            if(interval == null)
                throw new IllegalArgumentException("Error: cannot find interval value for intraday time series");

            return "Time Series ("+interval+")";
        }else if(informationKey.contains(dailyKey)){
            return "Time Series (Daily)";
        }else if(informationKey.contains(weeklyKey)){
            return "Weekly Time Series";
        }else if(informationKey.contains(monthlyKey)){
            return "Monthly Time Series";
        }

        throw new IllegalArgumentException("Error: bad information value: "+informationKey);
    }
}
