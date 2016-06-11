package io.github.gaiusandhartsolutions.newevolve;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/**
 * Created by erik.capistrano on 6/9/2016.
 */
public class App extends Application {
    // Variables
    private FirebaseDatabase fDatabase;
    private FirebaseAnalytics fAnalytics;
    private FirebaseAuth fAuth;
    private FirebaseRemoteConfig fRemoteConfig;
    private SharedPreferences LocalConfig;

    // Constructor
    public App(){
        super();
    }

    public void Initialize() {
        LocalConfig = getApplicationContext().
                getSharedPreferences(getString(R.string.app_localconfig), MODE_PRIVATE);
        fRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                //.setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        fRemoteConfig.setConfigSettings(configSettings);
        fRemoteConfig.fetch()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        fRemoteConfig.activateFetched();
                    }
                });
    }

    public FirebaseDatabase getDB() {
        if (fDatabase == null) {
            fDatabase = FirebaseDatabase.getInstance();
        }
        return fDatabase;
    }

    public FirebaseAnalytics getLogger() {
        if (fAnalytics == null) {
            fAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        }
        return fAnalytics;
    }

    public FirebaseAuth getAuth() {
        if (fAuth == null) {
            fAuth = FirebaseAuth.getInstance();
        }
        return fAuth;
    }

    public void logUITouch(String UIName, String UIType) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, UIName);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, UIType);
        this.getLogger().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public void logException(Exception ex) {
        FirebaseCrash.report(ex);
    }

    public String getFromRemoteConfig(String key) {
        return fRemoteConfig.getString(key);
    }

    public String getFromLocalConfig(String key) {
        return LocalConfig.getString(key, "");
    }

    public void setToLocalConfig(String key, String value) {
        SharedPreferences.Editor editor = LocalConfig.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
