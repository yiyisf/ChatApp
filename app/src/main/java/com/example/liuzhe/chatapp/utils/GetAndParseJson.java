package com.example.liuzhe.chatapp.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Message;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by liuzhe on 15/10/6.
 */
public class GetAndParseJson {
    public static final int PARSESUCCWSS=0x2001;
    private Handler handler;
    private String url;
    private Rate rates;

    public GetAndParseJson(Handler handler, String url){
        this.handler = handler;
        this.url = url;
        Log.i("In getandparse: url is ", url);
    };



    /**
     * 获取网络json
     * @author liuzhe
     *
     */
    public void getJsonFromInternet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");

                    if (httpURLConnection.getResponseCode()== 200){
                        InputStream inputStream = httpURLConnection.getInputStream();
//                        List<Rate> listRates = parseJson(inputStream);
                        Rate listRates = parseJson(inputStream);
//                        Log.i("In getandparse: rates ", String.valueOf(listRates.size()));
                        if (listRates != null){

                            Message msg = new Message();
                            msg.what = PARSESUCCWSS;
                            msg.obj = listRates;
                            handler.sendMessage(msg);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 解析json格式的输入流转换成List
     * @param inputStream
     * @return List
     */
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    protected List<Rate> parseJson(InputStream inputStream) {
    protected Rate parseJson(InputStream inputStream) {
        // TODO Auto-generated method stub
//        List<Rate> listRates=new ArrayList<Rate>();
        String jsonBytes=convertIsToByteArray(inputStream);
        String json=new String(jsonBytes);
        try {
//            JSONArray jsonArray=new JSONArray(json);
            JSONObject jsonArray=new JSONObject(json);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jObject=jsonArray.getJSONObject(i);
                String base=jsonArray.getString("base");
                Log.i("In getjson base is: ",base);
                String date=jsonArray.getString("date");
                Log.i("In getjson date is: ",date);
                JSONObject ratelist= jsonArray.getJSONObject("rates");
                Log.i("In getjson rates is: ",ratelist.toString());
                rates =new Rate(base, date, ratelist);
//                listRates.add(rates);
//            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        return listRates;
        Log.i("In Get base is :",rates.getBase());
        return rates;
    }
    /**
     * 将输入流转化成ByteArray
     * @param inputStream
     * @return ByteArray
     */
//    private byte[] convertIsToByteArray(InputStream inputStream) {
//        // TODO Auto-generated method stub
//        ByteArrayOutputStream baos=new ByteArrayOutputStream();
//        byte buffer[]=new byte[1024];
//        int length=0;
//        try {
//            while ((length=inputStream.read(buffer))!=-1) {
//                baos.write(buffer, 0, length);
//            }
//            inputStream.close();
//            baos.flush();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return baos.toByteArray();
//    }

    /**
     * 将输入流转化成ByteArray
     * @param inputStream
     * @return ByteArray
     */
    private String convertIsToByteArray(InputStream inputStream) {
        // TODO Auto-generated method stub
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte buffer[]=new byte[1024];
        int length=0;
        try {
            while ((length=inputStream.read(buffer))!=-1) {
                baos.write(buffer, 0, length);
            }
            inputStream.close();
            baos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baos.toString();
    }

}
