package com.example.myapplication.util;

import android.app.Activity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import androidx.multidex.MultiDexApplication;

/**
 * Created by wpa3 on 4/18/2018.
 */

public class ApplicationClass extends MultiDexApplication {

    private static ApplicationClass sInstance;
    private RequestQueue mRequestQueue;
    private Activity currentClass = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mRequestQueue = Volley.newRequestQueue(this);
    }

    public static synchronized ApplicationClass getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public void setCurrentClass(Activity currentClass) {
        this.currentClass = currentClass;
    }

    public Activity getCurrentClass() {
        return currentClass;
    }
}