package de.neoklosch.android.aevidonationapp;

import android.app.Application;


public class AeviDonateionAppApplication extends Application {
    private static volatile AeviDonateionAppApplication instance;

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance
     */
    public static AeviDonateionAppApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SharedPreferencesHelper.putBooleanInitial(this, Constants.SHARED_PREFERENCES_KEY_SETUP_DONE, false);
    }
}
