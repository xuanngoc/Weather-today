package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;


public class WeatherLoader extends AsyncTaskLoader<Weather> {

    /** Query URL*/
    private String mUrl;

    public WeatherLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public Weather loadInBackground() {
        if(mUrl == null){
            return null;
        }

        //Perform the network request, parse the response, and extract a weather
        Weather weather = QueryUtils.fetchWeatherDate(mUrl);
        return  weather;
    }

}
