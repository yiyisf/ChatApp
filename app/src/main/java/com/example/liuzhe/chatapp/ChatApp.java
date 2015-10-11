package com.example.liuzhe.chatapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by liuzhe on 15/10/2.
 */
public class ChatApp extends Application{
    
    public static ChatApp chatApp;

    public static ChatApp getInstance() {
        return chatApp;
    }

//    private static final int GO_HOME = 100;
//    private static final int GO_LOGIN = 200;

//    ParseUser parseUser;
    @Override
    public void onCreate() {
        super.onCreate();
//        Log.i("oncreate:", "created");
        chatApp = this;
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "uyKLnnhSiC7B9p7Gk5rE56D8Gfjy9expfYVlL6My", "QTiKCr0xPVAkm90nchl3DM6h5yuU10i35tR5Q0Lj");
//        ParseUser.enableAutomaticUser();
//        ParseACL defaultAct = new ParseACL();
//        ParseACL.setDefaultACL(defaultAct,true);
    }


}
