package com.zjc.drivingSchoolT.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingSchoolT.db.model.AccountBalance;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AccountBalanceParser extends JsonParser<AccountBalance> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<AccountBalance>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<AccountBalance>>() {
        }.getType();
    }

}
