package io.github.gaiusandhartsolutions.newevolve;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by erik.capistrano on 6/9/2016.
 */
public class App extends Application {
    // Constants
    public static final String AUTH_NAME = "AUTH_NAME";
    public static final String AUTH_EMAIL = "AUTH_EMAIL";
    public static final String AUTH_PHOTOURL = "AUTH_PHOTOURL";
    // Variables
    private Context context;
    private FirebaseAnalytics fAnalytics;
    private FirebaseAuth fAuth;
    private SharedPreferences LDB;

    // Constructor
    public App(){
        super();
    }

    public void setContext(Context context) {
        this.context = context;
        this.LDB = context.getSharedPreferences(getString(R.string.app_ldb), MODE_PRIVATE);
    }

    public FirebaseAnalytics getLogger() {
        return this.getLogger(this.context);
    }

    public FirebaseAnalytics getLogger(Context x) {
        if (fAnalytics == null) {
            fAnalytics = FirebaseAnalytics.getInstance(x);
        }
        return this.fAnalytics;
    }

    public FirebaseAuth getAuth() {
        if (fAuth == null) {
            fAuth = FirebaseAuth.getInstance();
        }
        return this.fAuth;
    }

    public void logUITouch(String UIName, String UIType) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, UIName);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, UIType);
        this.getLogger().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public String getFromLDB(String key) {
        return LDB.getString(key, "");
    }

    public void setToLDB(String key, String value) {
        SharedPreferences.Editor editor = LDB.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
