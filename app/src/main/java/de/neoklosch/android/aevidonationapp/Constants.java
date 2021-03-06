package de.neoklosch.android.aevidonationapp;

public final class Constants {
    private Constants() {
        throw new AssertionError();
    }

    public final static String SHARED_PREFERENCES_FILE = "aevi-donation-app-shared-preferences";

    public final static String SHARED_PREFERENCES_KEY_SETUP_DONE = "SHARED_PREFERENCES_KEY_SETUP_DONE";
    public final static String SHARED_PREFERENCES_KEY_CHARITY_NAME = "SHARED_PREFERENCES_KEY_CHARITY_NAME";
    public final static String SHARED_PREFERENCES_KEY_DESCRIPTION = "SHARED_PREFERENCES_KEY_DESCRIPTION";
    public final static String SHARED_PREFERENCES_KEY_DEFAULT_AMOUNT = "SHARED_PREFERENCES_KEY_DEFAULT_AMOUNT";
    public final static String SHARED_PREFERENCES_KEY_CHARITY_IMAGE = "SHARED_PREFERENCES_KEY_CHARITY_IMAGE";
    public final static String SHARED_PREFERENCES_KEY_DONATE_BUTTON_IMAGE = "SHARED_PREFERENCES_KEY_DONATE_BUTTON_IMAGE";

    public final static int AUTHENTICATION_REQUEST_CODE = 1;
    public final static int PAYMENT_REQUEST_CODE = 2;
    public final static int CHARITY_IMAGE_CHOOSER_REQUEST_CODE = 3;
    public final static int DONATE_BUTTON_IMAGE_CHOOSER_REQUEST_CODE = 4;
}
