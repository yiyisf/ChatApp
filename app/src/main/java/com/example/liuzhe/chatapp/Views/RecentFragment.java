package com.example.liuzhe.chatapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuzhe.chatapp.R;


/**
 * Created by liuzhe on 15/10/5.
 */
public class RecentFragment extends Fragment {

    private String rateapi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//        rateapi = "http://api.fixer.io/latest?base=USD";
//        getrate();
//    }
//
//    private void getrate() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(rateapi).openConnection();
//                    httpURLConnection.setConnectTimeout(5000);
//                    httpURLConnection.setRequestMethod("GET");
//
//                    if (httpURLConnection.getResponseCode()== 200){
//                        InputStream rates = httpURLConnection.getInputStream();
//                        TextView DisplayRate = (TextView) getActivity().findViewById(R.id.textView3);
//
//                        DisplayRate.setText(rates.toString());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();

//    }
}
