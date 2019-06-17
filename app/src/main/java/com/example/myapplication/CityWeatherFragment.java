package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CityWeatherFragment extends Fragment implements LoaderManager.LoaderCallbacks<Weather> {

    private static final String USGS_REQUEST_URL =
            "https://api.openweathermap.org/data/2.5/weather?q=dong%20hoi&lang=vi&appid=f5a089622fc3d2c656b395aa84ba6b89";

    private static final int WEATHER_LOADER_ID = 1;

    public static final String LOG_TAG = CityWeatherFragment.class.getSimpleName();


    View mView;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("STT", ""+ 1);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.slide_layout, container, false);

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(WEATHER_LOADER_ID, null, this);
        }else{
            Log.d("Network", "is error!");
        }
        return mView;
    }

    public static CityWeatherFragment newInstance(int pos) {

        CityWeatherFragment fm = new CityWeatherFragment();


        Bundle bb = new Bundle();
        bb.putString("msg", "ssss" );

        fm.setArguments(bb);

        Log.d("xxxxxxxxx", LOG_TAG);
        //

        return fm;
    }


    @NonNull
    @Override
    public Loader<Weather> onCreateLoader(int i, @Nullable Bundle bundle) {
        ///Log.d("Haixxxx", "Here");

        return new WeatherLoader(getContext(), USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Weather> loader, Weather weather) {


        ImageView imageStatusWeather = mView.findViewById(R.id.imageStatusWeatherView);
        new DownloadImageTask(imageStatusWeather).execute("http://openweathermap.org/img/w/"+weather.getWeatherIcon() +".png");


        TextView descriptAndTempView = mView.findViewById(R.id.mainAndTempView);
        String descriptAndTemp = weather.getWeatherDescription()
                + "  " + KelvinToCelsius(weather.getTemp())
                + " " + Html.fromHtml("&#8451");
        descriptAndTempView.setText(descriptAndTemp);
        Log.d("STT", ""+ 3333);

        TextView temp_minView = mView.findViewById(R.id.temp_minView);
        temp_minView.setText(KelvinToCelsius(weather.getTemp_min()) + " " + Html.fromHtml("&#8451" ));
        TextView temp_maxView = mView.findViewById(R.id.temp_maxView);
        temp_maxView.setText(KelvinToCelsius(weather.getTemp_max()) + " " + Html.fromHtml("&#8451" ));
        TextView atmView = mView.findViewById(R.id.atmView);
        atmView.setText(weather.getPressure() + " mb");
        TextView humidityView = mView.findViewById(R.id.humidityView);
        humidityView.setText(weather.getHumidity() + "%");
        TextView windView = mView.findViewById(R.id.windView);
        windView.setText(weather.getWindSpeed() + " mph");

        TextView sunsireView = mView.findViewById(R.id.sunsireView);
        sunsireView.setText( convertUnixTime(weather.getSunrise()) + " AM");
        TextView sunsetView = mView.findViewById(R.id.sunsetView);
        sunsetView.setText(convertUnixTime(weather.getSunset()) + " PM");



    }

    @Override
    public void onLoaderReset(@NonNull Loader<Weather> loader) {

    }

    private String KelvinToCelsius(double celsius){
        double kelvin =  (celsius - 273.15) ;
        return Double.toString((double) Math.round(kelvin * 10) / 10);

    }
    private String convertUnixTime(long unixSeconds){
        //Date unixSeconds = new Date( timeStamp *1000);
        Date date = new Date(unixSeconds*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");

        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getDefault());
        String formattedDate = sdf.format(date);

        return formattedDate;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
