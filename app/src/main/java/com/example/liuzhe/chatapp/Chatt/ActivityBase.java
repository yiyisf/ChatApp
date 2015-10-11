package com.example.liuzhe.chatapp.Chatt;

import android.content.Intent;
import android.os.Bundle;

import com.example.liuzhe.chatapp.Login;
import com.example.liuzhe.chatapp.custom.CustomActivity;
import com.parse.ParseUser;

/**
 * Created by liuzhe on 15/10/5.
 */
public class ActivityBase extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLogin();
    }

    public void checkLogin() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser.getCurrentUser() == null) {
            ShowToast("您的账号已在其他设备上登录!");
            startActivity(new Intent(this, Login.class));
            finish();
        }
    }
}
