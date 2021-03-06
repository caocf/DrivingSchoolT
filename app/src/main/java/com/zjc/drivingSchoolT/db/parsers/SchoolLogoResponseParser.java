package com.zjc.drivingSchoolT.db.parsers;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingSchoolT.db.response.SchoolLogoResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SchoolLogoResponseParser extends JsonParser<SchoolLogoResponse> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<SchoolLogoResponse>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<SchoolLogoResponse>>() {
        }.getType();
    }

}
