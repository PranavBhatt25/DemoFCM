package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.example.myapplication.util.ApplicationClass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Pranav on 06/02/2020.
 */

public class SplashActivity extends Activity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_splash);

        openActivity();
    }

    private void openActivity() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.demoplaystoreapplication", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                String KeyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        }, 3000);

    }

    @Override
    public void onResume() {
        super.onResume();
        ApplicationClass.getInstance().setCurrentClass(SplashActivity.this);
    }
}


