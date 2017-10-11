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
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Ming on 10/9/2017.
 */

public class SecurityTimeSeriesDeserializer implements JsonDeserializer<SecurityTimeSeriesModel> {

    public static final String TAG = "SecurityTimeSeriesDeserializer";

    @Override
    public SecurityTimeSeriesModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        SecurityTimeSeriesModel securityTimeSeriesModel = new SecurityTimeSeriesModel();

        JsonElement timeSeriesMap = json.getAsJsonObject().get("Time Series (Daily)");
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
}
