package com.example.gfc.gaidelclicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * An in-memory data store backed by shared preferences. This is a key-value store with a few important properties:
 * <br><br>
 * 1) Speed. Everything is stored in-memory so reads can happen on the UI thread. Writes and deletes happen
 * asynchronously (with callbacks). Every public method is safe to call from the UI thread.
 * <br><br>
 * 2) Durability. Writes get persisted to disk, so that this store maintains state even if the app closes or is killed.
 * <br><br>
 * 3) Consistency. Doing a write followed by a read should return the value you just put.
 * <br><br>
 * 4) Thread-safety. Reads and writes can happen from anywhere without the need for external synchronization.
 * <br><br>
 * Note that since writes are asynchronous, an in-flight write may be lost if the app is killed before the data has
 * been written to disk. If you require true 'commit' semantics then Prefs is not for you.
 * <br><br>
 * Created by mlapadula on 12/23/14.
 */
public class Prefs {

    private static final Prefs INSTANCE = new Prefs();

    private static final String TAG = Prefs.class.getSimpleName();

    /**
     * Lock to ensure that only one disk write happens at a time.
     */
    private static final Object SHARED_PREFS_LOCK = new Object();

    /**
     * Flag that ensures this store is initialized before using.
     */
    private volatile boolean mWasInitialized = false;

    /**
     * The context to use.
     */
    private volatile Context mAppContext;

    /**
     * The name of the shared preferences file to use.
     */
    private static String mSharedPrefsName;

    /**
     * Our data. This is a write-through cache of the data we're storing in SharedPreferences.
     */
    private ConcurrentMap<String, Object> mData;

    /**
     * Constructor.
     */
    private Prefs() {
        // Nothing to do here.
    }

    /**
     * Initializes this store with the given context.
     */
    private void initWithContext(Context context, String sharedPrefsName) {
        // Time ourselves
        long start = SystemClock.uptimeMillis();

        // Set vars
        mAppContext = context.getApplicationContext();
        mSharedPrefsName = sharedPrefsName;

        // Read from shared prefs
        SharedPreferences prefs = getSharedPreferences();
        mData = new ConcurrentHashMap<String, Object>();
        mData.putAll(prefs.getAll());
        mWasInitialized = true;

        long delta = SystemClock.uptimeMillis() - start;
        Log.i(TAG, "Prefs took " + delta + " ms to init");
    }

    /**
     * Initializes the store.
     *
     * @param context         the context to use. Using the application context is fine here.
     * @param sharedPrefsName the name of the shared prefs file to use
     * @return the singleton instance that was initialized.
     */
    public static synchronized Prefs init(Context context, String sharedPrefsName) {
        // Defensive checks
        if (context == null || TextUtils.isEmpty(sharedPrefsName)) {
            throw new RuntimeException(
                    "You must provide a valid context and shared prefs name when initializing Prefs");
        }

        // Initialize ourselves
        if (!INSTANCE.mWasInitialized) {
            INSTANCE.initWithContext(context, sharedPrefsName);
        }

        return INSTANCE;
    }

    /**
     * @return the singleton instance to use.
     */
    private static Prefs getInstance() {
        if (!INSTANCE.mWasInitialized) {
            throw new RuntimeException(
                    "Prefs was not initialized! You must call Prefs.init() before using this.");
        }
        return INSTANCE;
    }

    /**
     * Gets the shared preferences to use
     */
    private SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(mSharedPrefsName, Context.MODE_PRIVATE);
    }

    public static void syncAll() {
        getInstance().sync();
    }

    private void sync() {
        boolean success = false;
        synchronized (SHARED_PREFS_LOCK) {
            SharedPreferences.Editor editor = getSharedPreferences().edit();
            boolean everyDidPut = true;
            for (Map.Entry<String, Object> entry : mData.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Float) {
                    editor.putFloat(key, (Float) value);

                } else if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);

                } else if (value instanceof Long) {
                    editor.putLong(key, (Long) value);

                } else if (value instanceof String) {
                    editor.putString(key, (String) value);

                } else if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);

                } else {
                    everyDidPut = false;
                }
            }
            if (everyDidPut) {
                success = editor.commit();
            }
        }
        if (!success) throw new RuntimeException("Smth wrong");
    }

    /**
     * Saves the given (key,value) pair to memory and (asynchronously) to disk.
     *
     * @param key      the key
     * @param value    the value to put. This MUST be a type supported by SharedPreferences. Which is to say: one of (float,
     *                 int, long, String, boolean).
     * @return this instance
     */
    private <T> Prefs saveAsync(final String key, final T value) {
        // Put it in memory
        mData.put(key, value);
        return this;
    }

    /**
     * Put a float. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Prefs putFloat(final String key, final float value) {
        return getInstance().saveAsync(key, value);
    }

    /**
     * Put an int. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Prefs putInt(String key, int value) {
        return getInstance().saveAsync(key, value);
    }

    /**
     * Put a long. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Prefs putLong(String key, long value) {
        return getInstance().saveAsync(key, value);
    }

    /**
     * Put a String. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Prefs putString(String key, String value) {
        return getInstance().saveAsync(key, value);
    }

    /**
     * Put a boolean. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Prefs putBoolean(String key, boolean value) {
        return getInstance().saveAsync(key, value);
    }

    /**
     * Gets a float with the given key. Defers to the fallback value if the mapping didn't exist, wasn't a float,
     * or was null.
     */
    public static float getFloat(String key, float fallback) {
        Float value = getInstance().get(key, Float.class);
        return value != null ? value : fallback;
    }

    /**
     * Gets an int with the given key. Defers to the fallback value if the mapping didn't exist, wasn't an int,
     * or was null.
     */
    public static int getInt(String key, int fallback) {
        Integer value = getInstance().get(key, Integer.class);
        return value != null ? value : fallback;
    }

    /**
     * Gets a long with the given key. Defers to the fallback value if the mapping didn't exist, wasn't a long,
     * or was null.
     */
    public static long getLong(String key, long fallback) {
        Long value = getInstance().get(key, Long.class);
        return value != null ? value : fallback;
    }

    /**
     * Gets a String with the given key. Defers to the fallback value if the mapping didn't exist, wasn't a String,
     * or was null.
     */
    public static String getString(String key, String fallback) {
        String value = getInstance().get(key, String.class);
        return value != null ? value : fallback;
    }

    /**
     * Gets a boolean with the given key. Defers to the fallback value if the mapping didn't exist, wasn't a boolean,
     * or was null.
     */
    public static boolean getBoolean(String key, boolean fallback) {
        Boolean value = getInstance().get(key, Boolean.class);
        return value != null ? value : fallback;
    }

    /**
     * Determines if we have a mapping for the given key.
     *
     * @return true if we have a mapping for the given key
     */
    public static boolean containsKey(String key) {
        return getInstance().mData.containsKey(key);
    }

    /**
     * Gets the value mapped by the given key, casted to the given class. If the value doesn't exist or isn't of the
     * right class, return null instead.
     */
    private <T> T get(String key, Class<T> clazz) {
        Object value = mData.get(key);
        T castedObject = null;
        if (clazz.isInstance(value)) {
            castedObject = clazz.cast(value);
        }
        return castedObject;
    }

}
