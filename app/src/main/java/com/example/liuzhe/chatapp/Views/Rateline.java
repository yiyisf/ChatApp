package com.example.liuzhe.chatapp.Views;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhe on 15/10/7.
 */
public class Rateline {
    String cur;
    double rate;

    public Rateline(String cur, double rate){
        this.cur = cur;
        this.rate = rate;
    }

    public Map<String, Double> Getrate_map(){
        Map<String, Double> map = new HashMap<String, Double>();
        map.put(this.cur, this.rate);
        return map;
    }
}
