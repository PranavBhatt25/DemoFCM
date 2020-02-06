package com.example.myapplication.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wpa3 on 4/18/2018.
 */

public class Common {

    private static AlertDialog authProgressDialog;


    public static String divideTwoStrings(String string1, String string2) {
        BigDecimal a = new BigDecimal(string1);
        BigDecimal b = new BigDecimal(string2);
        return a.divide(b, a.scale(), RoundingMode.HALF_UP).toString();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static String getFormatedCurrency(BigDecimal mPrice) {

        Locale locale = new Locale("en");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setCurrency(Currency.getInstance("USD"));
        String mFormetedCurrency = format.format(mPrice);

        mFormetedCurrency = mFormetedCurrency.replaceAll("[^0-9,.\\s]", "");
        String lastCharDot = mFormetedCurrency.substring(mFormetedCurrency.length() - 1);
        if (lastCharDot.equals(".")) {
            mFormetedCurrency = mFormetedCurrency.replace(".", "").trim();
        }
        return mFormetedCurrency;
    }

    public static String getShopifyFormatedCurrency(String moneyFormat, BigDecimal mPrice) {
        String output = "";

        String FormetedCurrency = getFormatedCurrency(mPrice);
        if (moneyFormat.contains("{{ amount }}") || moneyFormat.contains("{{amount}}")) {
            output = FormetedCurrency;
        } else if (moneyFormat.contains("{{ amount_no_decimals }}") || moneyFormat.contains("{{amount_no_decimals}}")) {
            String[] formateOne = FormetedCurrency.replace(".", "-").split("-");
            output = formateOne[0];
        } else if (moneyFormat.contains("{{ amount_with_comma_separator }}") || moneyFormat.contains("{{amount_with_comma_separator}}")) {

            String[] formateOne = FormetedCurrency.replace(".", "-").split("-");
            output = formateOne[0].replace(",", ".") + "," + formateOne[1];

        } else if (moneyFormat.contains("{{ amount_no_decimals_with_comma_separator }}") || moneyFormat.contains("{{amount_no_decimals_with_comma_separator}}")) {
            String[] formateOne = FormetedCurrency.replace(".", "-").split("-");
            output = formateOne[0].replace(",", ".");

        } else if (moneyFormat.contains("{{ amount_with_apostrophe_separator }}") || moneyFormat.contains("{{amount_with_apostrophe_separator}}")) {
            output = FormetedCurrency.replace(",", "\'");
        } else {
            output = FormetedCurrency;
        }
        return output;
    }


    public static boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }


    /**
     * <b>Description</> Dismiss Progress Dialog
     */
    public static void ProgressDialogDismiss() {
        if (authProgressDialog != null && authProgressDialog.isShowing()) {
            authProgressDialog.dismiss();
        }
    }

    /**
     * <b>Description</b> - hide soft keyboard
     *
     * @param context
     * @param view
     */
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    /**
     * <b>Description</b> - Connection time out for calling API
     *
     * @param request
     */
    public static void setVolleyConnectionTimeout(JsonObjectRequest request) {
        int socketTimeout = 100000;//1 Meinute - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
    }

    public static void setVolleyConnectionTimeout(JsonArrayRequest request) {
        int socketTimeout = 100000;//1 Meinute - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
    }

    public static void setVolleyConnectionTimeout(StringRequest request) {
        int socketTimeout = 100000;//1 Meinute - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
    }

    /**
     * @return string
     * <p/>
     * Rinkesh
     * @Description getting the device id
     */

    public static String getDeviceId(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        return deviceId;
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }

    }

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(int dp, Context mContext) {
        Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * Initialize SSL
     *
     * @param mContext
     */
    public static void initializeSSLContext(Context mContext) {
        try {
            SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            ProviderInstaller.installIfNeeded(mContext.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public static SortedMap<Currency, Locale> currencyLocaleMap;

    static {
        currencyLocaleMap = new TreeMap<Currency, Locale>(new Comparator<Currency>() {
            public int compare(Currency c1, Currency c2) {
                return c1.getCurrencyCode().compareTo(c2.getCurrencyCode());
            }
        });
        for (Locale locale : Locale.getAvailableLocales()) {
            try {
                Currency currency = Currency.getInstance(locale);
                currencyLocaleMap.put(currency, locale);
            } catch (Exception e) {
            }
        }
    }

}
