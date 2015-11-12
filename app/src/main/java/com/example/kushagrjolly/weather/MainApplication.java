package com.example.kushagrjolly.weather;

import android.app.Application;

import com.johnhiott.darkskyandroidlib.ForecastApi;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ForecastApi.create("ff6ef961e828f55c404d2033bc8601bd");
    }
}
