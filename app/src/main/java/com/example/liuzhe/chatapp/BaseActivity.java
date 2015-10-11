package com.example.liuzhe.chatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.parse.ParseUser;


/**
 *基类
 *@author yiyi
 *@date
 *
 */

public class BaseActivity extends AppCompatActivity {

    ParseUser parseUser;
    protected int mScreenWidth;
    protected int mScreenHeigth;

    public Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseUser = ParseUser.getCurrentUser();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeigth = metrics.heightPixels;
        context = ChatApp.getInstance();

    }


    Toast mToast;

    /**
     * 公共处理，用于显示提示信息
     * @param text
     */
    public void ShowToast(final String text){
        if (!TextUtils.isEmpty(text)){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null){
                        mToast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    }else {
                        mToast.setText(text);
                    }

                    mToast.show();
                }
            });
        }

    }

    public void startAnimActivity(Class<?> cla) {
        this.startActivity(new Intent(this, cla));
    }

    public void startAnimActivity(Intent intent) {
        this.startActivity(intent);
    }

}
