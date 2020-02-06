package com.example.myapplication.util;

public interface Constant {


    public static final class MESSAGE {
        public static String CHECK_INTERNET_CONNECTION = "Please Check your internet connection";
        public static final String PROGRESS_PLEASE_WAIT_MSG = "Please Wait...";
        public static final String CONNECTION_TIMEOUT = "Connection timeout";
        public static final String STORE_ERROR = "Your subscription not active";
        public static final String STORE_NOT_FOUND = "Store not found";
        public static final String PASSWORD_WRONG = "Wrong Password";
    }

    public static final class WEB_SERVICE_KEY {
        public static final String FCM_TOKEN = "fcm_token";
        public static final String SHOP_DOMAIN = "shop_domain";
        public static final String DEVICEID = "deviceid";
        public static final String DEVICE_TYPE = "device_type";
    }

    public static final class URL {
        public static final String FCM_TOKEN_URL = "https://appbuilder.webplanex.com/apiStore";
    }

    public static final class JSON_KEY {
        public static String CODE = "code";
        public static String STATUS = "status";
        public static String SUCCESS = "success";
        public static String MSG = "msg";
        public static String MASSAGE = "message";
        public static String RESPONSE = "response";
        public static String DATA = "data";

    }
}
