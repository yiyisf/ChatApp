package com.example.liuzhe.chatapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.liuzhe.chatapp.Views.ContactFragment;
import com.example.liuzhe.chatapp.Views.MapFragment;
import com.example.liuzhe.chatapp.Views.Rateline;
import com.example.liuzhe.chatapp.Views.RecentFragment;
import com.example.liuzhe.chatapp.Views.SettingsFragment;
import com.example.liuzhe.chatapp.utils.GetAndParseJson;
import com.example.liuzhe.chatapp.utils.Rate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends BaseActivity {

    private Button[] mTabs;
    private Fragment[] fragments;
    private ContactFragment contactFragment;
    private RecentFragment recentFragment;
    private SettingsFragment settingFragment;
    private ImageView iv_recent_tips;
    private ImageView iv_contact_tips;
    private MapFragment mapFragment;
    private int index;
    private int currentTabIndex;
    private double latitude;
    private double longitudel;
    private ListView listView;
    private JSONObject getedRates;
//    Rateline[] ratelists;
//    private ArrayList<Map<String,Double>> ratelists;
    private ArrayList<Rate> ratelists;


    //    private List<Rate> listRates;
//    private Rate listRates;
    private Rate listRates;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case GetAndParseJson.PARSESUCCWSS:
//                    listRates = (List<Rate>) msg.obj;
                    listRates = (Rate) msg.obj;
                    initData();
                    break;
            }
        }
    };
    private String Rates;
    private String rateapi;
    private String cur;

    private void initData() {
//
//        List<Map<String, Object>>items=new ArrayList<Map<String,Object>>();
//        for (Rate rates:listRates) {
//            Map<String, Object>item=new HashMap<String, Object>();
//            item.put("base", rates.getBase());
//            item.put("date", rates.getDate());
            Log.i("In main base is :",listRates.getBase());
////            item.put("rates", convertDate(rates.getRates()));
//            items.add(item);
//        }
//            item.put("base", rates.getBase());
//            item.put("date", rates.getDate());
            Log.i("In main base is :", listRates.getBase());
        TextView Basecur = (TextView) findViewById(R.id.Base);
        Basecur.setText(listRates.getBase());

        TextView BaseDate = (TextView) findViewById(R.id.Date);
        BaseDate.setText(listRates.getDate());

        getedRates = listRates.getRates();
        ratelists = new ArrayList<Rate>(getedRates.length());
        Iterator it = getedRates.keys();

        while (it.hasNext()) {
            cur = it.next().toString();
            try {
                ratelists.add(new Rate(cur, getedRates.getDouble(cur)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        Log.i("In main rate: ", "is" + ratelists);
        ListView Rlsit = (ListView) findViewById(R.id.Rate);
        Rlsit.setAdapter(new RateAdapter());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getrate();
        initView();
        initTab();
    }

    private void getrate() {
        rateapi = this.getResources().getString(R.string.RateApi);
        Log.i("In main api link is: ",rateapi);
        GetAndParseJson getAndParseJson = new GetAndParseJson(handler, rateapi);
        getAndParseJson.getJsonFromInternet();
    }

    private void initView() {
        mTabs = new Button[3];
        mTabs[0] = (Button) findViewById(R.id.btn_message);
        mTabs[1] = (Button) findViewById(R.id.btn_contract);
        mTabs[2] = (Button) findViewById(R.id.btn_set);
        iv_recent_tips = (ImageView) findViewById(R.id.iv_recent_tips);
        iv_contact_tips = (ImageView) findViewById(R.id.iv_contact_tips);
        //把第一个tab设为选中状态
        mTabs[0].setSelected(true);
        currentTabIndex = 0;

    }

    private void initTab() {
        contactFragment = new ContactFragment();

        recentFragment = new RecentFragment();
        settingFragment = new SettingsFragment();
        fragments = new Fragment[]{recentFragment, contactFragment, settingFragment};
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, recentFragment).
                add(R.id.fragment_container, contactFragment).hide(contactFragment).show(recentFragment).commit();
    }

    public void onTabSelect(View v) {
        switch (v.getId()) {
            case R.id.btn_message:
                index = 0;
                break;
            case R.id.btn_contract:
                index = 1;
                break;
            case R.id.btn_set:
                index = 2;
                break;
            case R.id.btn_map:
                Log.i("manacticty:", "点击了按钮" + index);
//                index = 3;
                latitude = contactFragment.latitude;
                longitudel = contactFragment.longitudel;
                Intent I = new Intent(this, MapFragment.class);
                I.putExtra("la", latitude);
                I.putExtra("longi", longitudel);
                startAnimActivity(I);
                break;
        }

        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        //把当前tab设为选中状态
        mTabs[index].setSelected(true);

        currentTabIndex = index;

    }


    private class RateAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return getedRates.length();
        }

        @Override
        public Rate getItem(int position) {
//            return null;
            return ratelists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.rate_line, null);

            Rate r = getItem(position);
            TextView cur = (TextView) convertView.findViewById(R.id.cur);
            cur.setText(r.getCur());

            TextView rate = (TextView) convertView.findViewById(R.id.rate1);
            rate.setText(r.getRate().toString());


            return convertView;
        }
    }
}
