package com.example.myapplication.FCM;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.util.AppPreference;
import com.example.myapplication.util.ApplicationClass;
import com.example.myapplication.util.Common;
import com.example.myapplication.util.Constant;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.myapplication.util.Constant.URL.FCM_TOKEN_URL;
import static com.example.myapplication.util.Constant.WEB_SERVICE_KEY.DEVICEID;
import static com.example.myapplication.util.Constant.WEB_SERVICE_KEY.DEVICE_TYPE;
import static com.example.myapplication.util.Constant.WEB_SERVICE_KEY.FCM_TOKEN;
import static com.example.myapplication.util.Constant.WEB_SERVICE_KEY.SHOP_DOMAIN;
//import static com.wpa3.productshopify.util.Constant.WEB_SERVICE_KEY.SHOPE_DOMAIN;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    Context context;

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        context = getApplicationContext();

        AppPreference.setStringPref(context, AppPreference.PREF_SIGNUP_FCM_ID, AppPreference.PREF_KEY.PREF_KEY_FCM_ID, refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    // [END refresh_token]


    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.

        Map<String, String> params = new HashMap<String, String>();
        String device_type = "1";
        String device_id = Common.getDeviceId(this);
        params.put(FCM_TOKEN, token);
        params.put(DEVICEID, device_id);
        params.put(DEVICE_TYPE, device_type);
        JsonObjectRequest request = new JsonObjectRequest(FCM_TOKEN_URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            parseJsonPersonalDetail(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            NetworkResponse response = error.networkResponse;

                            Log.d("testerror", "" + statusCode + " " + response.data);
//                            Toast.makeText(SignUpActivity.this, Constant.MESSAGE.CONNECTION_TIMEOUT, Toast.LENGTH_LONG).show();
                        }
//                        Common.ProgressDialogDismiss();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-agent", "Mozilla/5.0 (TV; rv:44.0) Gecko/44.0 Firefox/44.0");
                return headers;
            }
        };

        Common.setVolleyConnectionTimeout(request);
        ApplicationClass.getInstance().getRequestQueue().add(request);

    }


    /**
     * <b>Description</b> - Get back response for calling  callUserDetailSave API
     *
     * @param jsonObject - Pass API response
     */
    private void parseJsonPersonalDetail(JSONObject jsonObject) {
        try {

//            Common.ProgressDialogDismiss();
            Log.i("get response", "get response" + jsonObject);
            if (jsonObject.toString().contains(Constant.JSON_KEY.MSG)) {
                String message = jsonObject.getString(Constant.JSON_KEY.MSG);
                String status = jsonObject.getString(Constant.JSON_KEY.CODE);

//                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
