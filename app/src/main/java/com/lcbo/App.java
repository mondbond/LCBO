package com.lcbo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.lcbo.di.AppComponent;
import com.lcbo.di.AppModule;
import com.lcbo.di.DaggerAppComponent;
import com.lcbo.model.db.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private final String INIT = "init";
    private final String TIME_DB_RECORD = "timeDbRecord";
    private final long NO_DELETE_PERIOD = 86400000;

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();

        long currentTime = System.currentTimeMillis();
        SharedPreferences sharedPreferences = this.getSharedPreferences(INIT, Context.MODE_PRIVATE);
        long lastTimeVisit = sharedPreferences.getLong(TIME_DB_RECORD, 0);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "lcbo", null);
        Database db = helper.getWritableDb();

//        if last visit was more then day ago - clean all data
        if(lastTimeVisit != 0 && currentTime - lastTimeVisit > NO_DELETE_PERIOD){
            DaoMaster.dropAllTables(db, true);
            DaoMaster.createAllTables(db, true);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(TIME_DB_RECORD, currentTime);
        editor.apply();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}
