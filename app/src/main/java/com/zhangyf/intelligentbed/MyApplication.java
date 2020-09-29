package com.zhangyf.intelligentbed;

import android.app.Application;

import java.net.Socket;

public class MyApplication extends Application {

    static MyApplication instance;
    Socket socket;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null)
            instance = this;
    }
}
