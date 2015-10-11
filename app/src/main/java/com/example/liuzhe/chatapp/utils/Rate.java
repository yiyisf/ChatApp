package com.example.liuzhe.chatapp.utils;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzhe on 15/10/6.
 */
public class Rate  {
    private String base;
    private String date;
    private JSONObject arrayList;
    private String cur;
    private Double rate;

    public Rate (String base,String date,JSONObject arrayList){
        this.base = base;
        this.date = date;
        this.arrayList = arrayList;
    }

    public Rate (String cur,Double rate){
        this.cur = cur;
        this.rate = rate;
    }


    public String getBase() {
        return this.base;
    }

    public String getDate() {
        return this.date;
    }

    public JSONObject getRates() {
        return this.arrayList;
    }

    public String getCur(){
        return this.cur;
    }

    public Double getRate(){
        return this.rate;
    }
}
