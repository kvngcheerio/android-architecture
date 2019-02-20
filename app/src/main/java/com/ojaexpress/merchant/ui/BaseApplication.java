package com.ojaexpress.merchant.ui;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

/**
 * Created by funso on 04/06/2018.
 * <p>
 * Peace
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        new Instabug.Builder(this, "08e64fa387688141c819afd53386a1cf")
                .setInvocationEvent(InstabugInvocationEvent.SHAKE)
                .build();
    }
}
