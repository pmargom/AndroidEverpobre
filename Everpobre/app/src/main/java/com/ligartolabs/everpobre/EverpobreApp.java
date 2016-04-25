package com.ligartolabs.everpobre;

import android.app.Application;
import android.util.Log;

import com.ligartolabs.everpobre.model.db.DBConstants;
import com.ligartolabs.everpobre.model.db.DBHelper;

/**
 * Created by pmargom on 18/4/16.
 */
public class EverpobreApp extends Application {
    public static final String TAG = EverpobreApp.class.getName();

    // aquí podemos meter todo el código que queremos hacer antes de cargar el primer activity
    // es similar al AppDelegate en iOS


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Starting Everpobre app...");
        DBHelper.configure(DBConstants.DBNAME, getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
