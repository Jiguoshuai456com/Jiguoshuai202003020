package com.bw.jiguoshuai202003020;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
