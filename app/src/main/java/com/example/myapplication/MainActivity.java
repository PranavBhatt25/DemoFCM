package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.util.AppPreference;

public class MainActivity extends Activity {
    Context context;
    TextView tv_device_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        tv_device_id = findViewById(R.id.tv_device_id);
        String mFcmId = AppPreference.getStringPref(context, AppPreference.PREF_SIGNUP_FCM_ID, AppPreference.PREF_KEY.PREF_KEY_FCM_ID);
        tv_device_id.setText("FCM ID:-  "+mFcmId);
    }
}
