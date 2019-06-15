package com.example.myapplication;

import android.app.LoaderManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Weather> {

    public static LinearLayout rootLayout;
    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private SlideAdapter mSlideAdapter;
    private TextView[] mDots;

    /*public static final int[] backgroundColor = {
            Color.RED,
            Color.BLUE,
            Color.YELLOW,
            Color.GRAY,
            Color.MAGENTA
    };*/

    private static final String USGS_REQUEST_URL =
            "https://api.openweathermap.org/data/2.5/weather?q=dong%20hoi&lang=vi&appid=f5a089622fc3d2c656b395aa84ba6b89";

    private static final int WEATHER_LOADER_ID = 1;

    /*private ImageView imageStatusWeather;
    private TextView mainAndTempView;
    private TextView temp_minView;
    private TextView temp_maxView;

    private TextView atmView;
    private TextView humidityView;
    private TextView windView;

    private TextView sunsireView;
    private TextView sunsetView;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);

        rootLayout = findViewById(R.id.rootLayout);
        //rootLayout.setBackgroundColor(backgroundColor[3]);

        mSlideViewPager = findViewById(R.id.viewPager);
        mDotsLayout = findViewById(R.id.dots);
        mSlideAdapter = new SlideAdapter(this);
        mSlideViewPager.setAdapter(mSlideAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        /*///
        imageStatusWeather = findViewById(R.id.imageStatusWeatherView);
        mainAndTempView = findViewById(R.id.mainAndTempView);
        //mainAndTempView.setText("xxxxxxxxxx");
        mainAndTempView.setText(Html.fromHtml("&#8226"));

        temp_minView = findViewById(R.id.temp_minView);
        temp_maxView = findViewById(R.id.temp_maxView);

        atmView = findViewById(R.id.atmView);
        humidityView = findViewById(R.id.humidityView);
        windView = findViewById(R.id.windView);

        sunsireView = findViewById(R.id.sunsireView);
        sunsetView = findViewById(R.id.sunsetView);
*/


        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(WEATHER_LOADER_ID, null, this);
    }

    public void addDotsIndicator(int pos) {
        mDots = new TextView[5];
        mDotsLayout.removeAllViews();
        for(int i=0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(20);
            mDots[i].setTextColor(Color.parseColor("#30336b"));
            mDotsLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[pos].setTextColor(Color.parseColor("#f9ca24"));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

            //rootLayout.setBackgroundColor(backgroundColor[i]);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id){
            case R.id.action_refresh:
                Toast.makeText(MainActivity.this, "Action refresh", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_add_city:
                Toast.makeText(MainActivity.this, "Action App city", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Action Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_fqa:
                Toast.makeText(MainActivity.this, "Action FQA", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }




    @NonNull
    @Override
    public Loader<Weather> onCreateLoader(int i, @Nullable Bundle bundle) {
        // Create a new loader for the given URL
        Log.d("hello", "Loader");
        return new WeatherLoader(this, USGS_REQUEST_URL );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Weather> loader, Weather weather) {
        Log.d("hello", "Loader");

        if(weather != null){
            // Set image status weather
            /*String icon = "01d";// weather.getWeatherIcon();

            URL url = null;
            try{
                url = new URL("http://openweathermap.org/img/w/"+ icon +".png");
            }catch (MalformedURLException e){
                Log.e("URL", "Problem building the URL", e);
            }

            //URL url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageStatusWeather.setImageBitmap(bmp);*/

            //Set main and temp
            String weatherMain = weather.getWeatherMain();
            double weatherTemp = weather.getTemp();
            String mainAndTemp = weatherMain + " " + weatherTemp + Html.fromHtml("&#8451");
            //mainAndTempView.setText("xxxxxxxxxxx");
            Log.d("xxxxxx",weatherMain );
            Log.d("xxxxxx", "" + weatherTemp);
            Log.d("xxxxxxxx", mainAndTemp);

        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Weather> loader) {

    }
}
