package com.zjc.drivingSchoolT.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingSchoolT.db.response.UserProductResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserProductResponseParser extends JsonParser<UserProductResponse> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<UserProductResponse>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<UserProductResponse>>() {
        }.getType();
    }

}
