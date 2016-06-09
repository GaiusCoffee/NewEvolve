package io.github.gaiusandhartsolutions.newevolve;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by erik.capistrano on 6/9/2016.
 */
public class App extends Application {
    private Context context;
    private FirebaseAnalytics fAnalytics;

    public App(){
        super();
    }

    public void setContext(Context context) {
        this.context = context;
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

    public void logUITouch(String UIName, String UIType) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, UIName);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, UIType);
        this.getLogger().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
