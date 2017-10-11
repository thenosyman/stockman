package com.thenosyman.stockman.stockman.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Ming on 10/9/2017.
 */

public class SecurityDeserializer implements JsonDeserializer<SecurityModel> {
    @Override
    public SecurityModel deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context) throws JsonParseException {
        SecurityModel securityModel = new SecurityModel();

        JsonElement open = json.getAsJsonObject().get("1. open");
        JsonElement high = json.getAsJsonObject().get("2. high");
        JsonElement low = json.getAsJsonObject().get("3. low");
        JsonElement close = json.getAsJsonObject().get("4. close");
        JsonElement volume = json.getAsJsonObject().get("5. volume");

        securityModel.setOpen(open.getAsFloat());
        securityModel.setHigh(high.getAsFloat());
        securityModel.setLow(low.getAsFloat());
        securityModel.setClose(close.getAsFloat());
        securityModel.setLow(volume.getAsFloat());

        return securityModel;
    }
}
