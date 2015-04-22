package de.neoklosch.android.aevidonationapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Map;

/**
 * Class {@link SharedPreferencesHelper} is a wrapper for the shared preferences.
 */
@SuppressWarnings("unused")
public final class SharedPreferencesHelper {
    /**
     * Wraps {@link android.content.SharedPreferences.OnSharedPreferenceChangeListener#onSharedPreferenceChanged(SharedPreferences, String)}
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param listener
     *            the listener, must not be null
     * @param key
     *            the key we want to run onSharedPreferenceChanged on, must not be null
     * @throws NullPointerException
     *             if listener or key is {@code null}
     */
    @SuppressWarnings("unused")
    public static void callListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener, String key) {
        if(listener == null) {
            throw new NullPointerException("Null listener");
        }
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        listener.onSharedPreferenceChanged(getPreferences(context), key);
    }

    /**
     * Wraps {@link android.content.SharedPreferences.Editor#clear()}. See its docs for clarifications. Calls
     * {@link android.content.SharedPreferences.Editor#commit()}
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @return true if the preferences were successfully cleared, false otherwise
     */
    @SuppressWarnings("unused")
    public static boolean clear(Context context) {
        return getPreferences(context).edit().clear().commit();
    }

    /**
     * Wraps {@link android.content.SharedPreferences#contains(String)}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @return true if the preferences contain the given key, false otherwise
     * @throws NullPointerException
     *             if key is {@code null}
     */
    @SuppressWarnings("unused")
    public static boolean contains(Context context, String key) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        return getPreferences(context).contains(key);
    }

    /**
     * Returns boolean value from the {@link SharedPreferences}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param defaultValue
     *            Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, defaultValue otherwise.
     */
    @SuppressWarnings("unused")
    public static boolean getBoolean(final Context context, final String key, final boolean defaultValue) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        return getPreferences(context).getBoolean(key, defaultValue);
    }

    /**
     * Returns float value from the {@link SharedPreferences}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param defaultValue
     *            Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, defaultValue otherwise.
     */
    @SuppressWarnings("unused")
    public static float getFloat(final Context context, final String key, final float defaultValue) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        return getPreferences(context).getFloat(key, defaultValue);
    }

    /**
     * Returns int value from the {@link SharedPreferences}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param defaultValue
     *            Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, defaultValue otherwise.
     */
    @SuppressWarnings("unused")
    public static int getInt(final Context context, final String key, final int defaultValue) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        return getPreferences(context).getInt(key, defaultValue);
    }

    /**
     * Returns long value from the {@link SharedPreferences}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param defaultValue
     *            Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, defaultValue otherwise.
     */
    @SuppressWarnings("unused")
    public static long getLong(final Context context, final String key, final long defaultValue) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        return getPreferences(context).getLong(key, defaultValue);
    }

    /**
     * Returns {@link String} value from the {@link SharedPreferences}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param defaultValue
     *            Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, defaultValue otherwise.
     */
    @SuppressWarnings("unused")
    public static String getString(final Context context, final String key, final String defaultValue) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        return getPreferences(context).getString(key, defaultValue);
    }

    /**
     * Wraps {@link android.content.SharedPreferences#getAll()}. Since you must not modify the collection returned by this method, or alter any of its contents,
     * this method returns an <em>unmodifiableMap</em> representing the preferences.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @return an <em>unmodifiableMap</em> containing a list of key/value pairs representing the preferences
     * @throws NullPointerException
     *             as per the docs of getAll() - does not say when
     */
    @SuppressWarnings("unused")
    public static Map<String, ?> getAll(Context context) {
        return Collections.unmodifiableMap(getPreferences(context).getAll());
    }

    private static SharedPreferences getPreferences(Context context) {
        SharedPreferences result = sharedPreferences;
        if(result == null) {
            synchronized(SharedPreferencesHelper.class) {
                result = sharedPreferences;
                if(result == null) {
                    sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
                    result = sharedPreferences;
                }
            }
        }
        return result;
    }

    /**
     * Wrapper around {@link android.content.SharedPreferences.Editor} {@code putBoolean()} methods.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param value
     *            the preference's value
     */
    @SuppressWarnings("unused")
    public static void putBoolean(final Context context, final String key, final boolean value) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Wrapper around {@link android.content.SharedPreferences.Editor} {@code putFloat()} methods.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param value
     *            the preference's value
     */
    @SuppressWarnings("unused")
    public static void putFloat(final Context context, final String key, final float value) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * Wrapper around {@link android.content.SharedPreferences.Editor} {@code putInt()} methods.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param value
     *            the preference's value
     */
    @SuppressWarnings("unused")
    public static void putInt(final Context context, final String key, final int value) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Wrapper around {@link android.content.SharedPreferences.Editor} {@code putLong()} methods.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param value
     *            the preference's value
     */
    @SuppressWarnings("unused")
    public static void putLong(final Context context, final String key, final long value) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * Wrapper around {@link android.content.SharedPreferences.Editor} {@code putString()} methods.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @param value
     *            the preference's value
     */
    @SuppressWarnings("unused")
    public static void putString(final Context context, final String key, final String value) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Wraps {@link android.content.SharedPreferences#registerOnSharedPreferenceChangeListener(android.content.SharedPreferences.OnSharedPreferenceChangeListener)}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param listener
     *            the listener, must not be null
     * @throws NullPointerException
     *             if listener is {@code null}
     */
    @SuppressWarnings("unused")
    public static void registerListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        if(listener == null) {
            throw new NullPointerException("Null listener");
        }
        getPreferences(context).registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Wraps {@link android.content.SharedPreferences.Editor#remove(String)}. See its docs for clarifications. Calls
     * {@link android.content.SharedPreferences.Editor#commit()}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param key
     *            the preference's key, must not be {@code null}
     * @return true if the key was successfully removed, false otherwise
     * @throws NullPointerException
     *             if key is {@code null}
     */
    @SuppressWarnings("unused")
    public static boolean remove(Context context, String key) {
        if(key == null) {
            throw new NullPointerException("Null keys are not permitted");
        }
        return getPreferences(context).edit().remove(key).commit();
    }

    /**
     * Wraps {@link android.content.SharedPreferences#unregisterOnSharedPreferenceChangeListener(android.content.SharedPreferences.OnSharedPreferenceChangeListener)}.
     *
     * @param context
     *            the context the SharedPreferences belong to
     * @param listener
     *            the listener, must not be null
     * @throws NullPointerException
     *             if listener is {@code null}
     */
    @SuppressWarnings("unused")
    public static void unregisterListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        if(listener == null) {
            throw new NullPointerException("Null listener");
        }
        getPreferences(context).unregisterOnSharedPreferenceChangeListener(listener);
    }

    private static SharedPreferences	sharedPreferences;

    private SharedPreferencesHelper() {
    }
}
