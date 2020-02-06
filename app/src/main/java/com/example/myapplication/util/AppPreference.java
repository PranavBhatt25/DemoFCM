package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wpa3 on 4/18/2018.
 */

public class AppPreference {

    public static final String PREF_FIRST_NAME = "prefFirstName";
    public static final String PREF_LAST_NAME = "prefLastName";
    public static final String PREF_EMAIL = "prefEmail";
    public static final String PREF_EMAIL_USER = "prefEmailUser";
    public static final String PREF_ID = "prefId";
    public static final String PREF_ACCESS_TOKEN_STORE = "prefAccessTokenStore";
    public static final String PREF_ACCESS_TOKEN_USER = "prefAccessTokenUser";
    public static final String PREF_EXPIRES_AT = "prefExpiresAt";
    public static final String PREF_ADDRESS = "prefExpiresAt";
    public static final String PREF_SIGNUP_FCM_ID = "prefUserFcmId";
    public static final String PREF_SHOP = "prefShop";
    public static final String PREF_SKIP = "prefSkip";
    public static final String PREF_IS_LOGIN = "prefIsLogin";
    public static final String PREF_IS_NOTI = "prefIsNoti";
    public static final String PREF_IS_INSTAGRAM_ACTIVE = "prefisInstagramActive";
    public static final String PREF_PHONE_NUMBER = "prefPhoneNumber";
    public static final String PREF_ONLY_PHONE_NUMBER = "prefOnlyPhoneNumber";
    public static final String PREF_COUNTRY_ISO = "prefCountryIso";
    public static final String PREF_LANGUAGE = "prefLanguage";
    public static final String PREF_LANGUAGE_CONSTANT = "prefLanguageConstant";
    public static final String PREF_MAIN_SETTING_LANGUAGE = "prefMainSettingLanguage";
    public static final String PREF_NOTI_MSG_TITLE = "prefNotiMsgTitle";
    public static final String PREF_NOTI_MSG_SUB_TITLE = "prefNotiMsgSubTitle";
    public static final String PREF_NOTI_DURATION = "prefNotiDuration";
    public static final String PREF_NOTI_STATUS = "prefNotiStatus";
    public static final String PREF_NOTI_STATUS_FROM_API = "prefNotiStatusFromApi";
    public static final String PREF_NOTI_IMAGE = "prefNotiImage";
    public static final String PREF_NOTI_IMAGE_64 = "prefNotiImage64";
    public static final String PREF_INSTAGRAM_TOKEN = "prefInstagramToken";
    public static final String PREF_IS_LANGUAGE_SELECTED = "prefIS LanguageSelected";
    public static final String PREF_LANGUAGE_ORIGINAL = "prefOriginalLanguage";
    public static final String PREF_SELECTED_FLAG = "prefSelectedFlag";
    public static final String PREF_ORIGINAL_FLAG = "prefOriginalFlag";
    public static final String PREF_IS_LISTVIEW_SELECTED = "prefIsListViewSelected";
    public static final String PREF_RATING_REVIEW_TOKEN = "ReviewRatingToken";
    public static final String PREF_AUTO_SLIDER = "PrefAutoSlider";
    public static final String PREF_AUTO_SLIDER_MILLII_SEC = "PrefAutoSliderMilliSec";
    public static final String PREF_VISUAL_SEARCH_ACTIVE = "PrefVisualSearchActive";
    public static final String PREF_VISUAL_SEARCH_TOKEN = "PrefVisualSearchToken";
    public static final String PREF_IS_USER_ACCOUNT_REQUIRED = "PrefIsUserAccountRequired";
    public static final String PREF_MONEY_FORMAT = "PrefMoneyFormat";
    public static final String PREF_IS_BOTTOM_MENU = "PrefIsNavigationMenu";
    public static final String PREF_IS_CUSTOM_COLLECTION= "PrefIsCustomCollection";
    public static final String PREF_IS_SUPPORT_FLOTTING_BUTTON= "PrefIsSupportFlottingButton";
    public static final String PREF_IS_CUSTOM_ATTRIBUTE= "PrefIsCustomAttribute";
    public static final String PREF_IS_BLOG= "PrefIsBlog";
    public static final String PREF_IS_BLOG_TITLE= "PrefIsBlogTitle";
    public static final String PREF_IS_PREVIOUS_SELECTED_CURERNCY_CODE= "PrefIsPrevSelectedCurrencyCode";



    public static final void setStringPref(Context context, String prefKey, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(prefKey, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static final String getStringPref(Context context, String prefName, String key) {
        SharedPreferences sp = context.getSharedPreferences(prefName, 0);
        return sp.getString(key, "");
    }

    public static final String getStringLangaugePref(Context context, String prefName, String key) {
        SharedPreferences sp = context.getSharedPreferences(prefName, 0);
        return sp.getString(key, "en");
    }

    public static final class PREF_KEY {
        public static final String PREF_KEY_CURRENCY_CODE = "currency_code";
        public static final String PREF_KEY_CURRENCY_SYMBOL = "currency_symbol";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL_USER = "emailUser";
        public static final String EMAIL = "email";
        public static final String ID = "id";
        public static final String ACCESS_TOKEN_STORE = "access_token_store";
        public static final String ACCESS_TOKEN_STORE_USER = "access_token_User";
        public static final String EXPIRES_AT = "expires_at";
        public static final String ADDRESS_1 = "address_1";
        public static final String ADDRESS_2 = "address_2";
        public static final String CITY = "city";
        public static final String PROVINCE = "province";
        public static final String ZIP = "zip";
        public static final String COUNTRY = "country";
        public static final String PHONE = "phone";
        public static final String PREF_KEY_FCM_ID = "fcmId";
        public static final String PREF_KEY_SHOP_DOMAIL = "shopDomain";
        public static final String PREF_KEY_API_KEY = "apiKey";
        public static final String PREF_KEY_APP_PASSWORD = "appPassword";
        public static final String PREF_KEY_APP_LOGO = "appLogo";
        public static final String PREF_KEY_SKIP = "isSkip";
        public static final String PREF_KEY_IS_LOGIN = "isLogin";
        public static final String PHONE_NUMBER = "PhoneNumber";
        public static final String ONLY_PHONE_NUMBER = "OnlyPhoneNumber";
        public static final String COUNTRY_ISO = "CountryIso";
        public static final String PREF_KEY_BTN_BG_COLOR = "btn_bg_color";
        public static final String PREF_KEY_BTN_TEXT_COLOR = "btn_text_color";
        public static final String PREF_KEY_SLIDER_COLOR = "slider_color";
        public static final String PREF_KEY_PRICE_COLOR = "price_color";
        public static final String PREF_KEY_COMPARE_PRICE_COLOR = "compare_price_color";
        public static final String PREF_KEY_PRODUCT_TITLE_COLOR = "product_title_color";
        public static final String LANGUAGE_SELECTED = "languageSelected";
        public static final String MAIN_SETTING_LANGUAGE = "mainSettingLaunage";
        public static final String PREF_NOTI_MSG_TITLE_KEY = "NotiMsgTitle";
        public static final String PREF_NOTI_MSG_SUB_TITLE_KEY = "NotiMsgSubTitle";
        public static final String PREF_NOTI_DUTAION_KEY = "NotiDuration";
        public static final String PREF_NOTI_STATUS_KEY = "NotiStatus";
        public static final String PREF_NOTI_STATUS_FROM_API_KEY = "NotiStatusFromApi";
        public static final String PREF_NOTI_IMAGE = "NotiImage";
        public static final String PREF_NOTI_IMAGE64 = "NotiImage64";
        public static final String PREF_INSTAGRAM_TOKEN_KEY = "NotiInstagramTokenKey";
        public static final String PREF_INSTAGRAM_ACTIVE = "PrefisInstaramActive";
        public static final String PREF_KEY_IS_NOTI = "prefKeyIsNoti";
        public static final String IS_LANGUAGE_SELECTED = "IslanguageSelected";
        public static final String PREF_LANGUAGE_CONSTANT_IS = "LanaguageConstatntIs";
        public static final String ORIGINAL_LANGUAGE = "OriginalLanguage";
        public static final String SELECTED_FLAG = "SelectedFlag";
        public static final String ORIGINAL_FLAG = "OriginalFlag";
        public static final String IS_LISTVIEW_SELECTED = "IsListviewSelected";
        public static final String RATING_REVIEW_TOKER = "RatingreviewToken";
        public static final String AUTO_SLIDER= "autoSlider";
        public static final String AUTO_SLIDER_MILLI_SEC = "autoSliderMilliSec";
        public static final String VISUAL_SEARCH_ACTIVE = "VisualSearchActive";
        public static final String VISUAL_SEARCH_TOKEN = "VisualSearchToken";
        public static final String IS_USER_ACCOUNT_REQUIRED = "isUserAccountRequired";
        public static final String MONEY_FORMAT = "MoneyFormat";
        public static final String IS_BOTTOM_MENU = "isBottomMenu";
        public static final String IS_CUSTOM_COLLECTION= "IsCustomCollection";
        public static final String IS_SUPPORT_FLOTTING_BUTTON= "IsSupportFlottingButton";
        public static final String IS_CUSTOM_ATTRIBUTE= "IsCustomAttribute";
        public static final String IS_PREVIOUS_SELECTED_CURERNCY_CODE= "IsPrevSelectedCurrencyCode";

        public static final String IS_BLOG= "IsBlog";
        public static final String IS_BLOG_TITLE= "IsBlogTitle";

    }
}
