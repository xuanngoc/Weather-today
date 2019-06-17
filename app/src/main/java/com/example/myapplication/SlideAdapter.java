package com.example.myapplication;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class SlideAdapter extends FragmentPagerAdapter {


    private CityWeatherFragment fragments;
    /*public Weather mWeather = new Weather("aaaa", "NHieu may, khong mua", "nong", 1.1, 1.1,
            1.1, 34, 24, 1, "NV", 23, 23, 12222, "DOng HOi");*/
    private Weather weather;
    public String[] slide = {
            "slide 0",
            "slide 1",
            "slide 2",
            "slide 3",
            "slide 4",};

    public SlideAdapter(FragmentManager fm, CityWeatherFragment fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int pos) {
        return new CityWeatherFragment();
    }

    @Override
    public int getCount() {
        return slide.length;
    }






    /*@Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        //Log.d("SlideApdapter", "x " + (view == o));

        return view ==  o;
    }*/
   /* @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false );

        ///


        //Log.d("xxxxxxxxx00", mWeather.getWeatherMain());

        *//*ImageView imageStatusWeather = view.findViewById(R.id.imageStatusWeatherView);

        TextView mainAndTempView = view.findViewById(R.id.mainAndTempView);
        mainAndTempView.setText(mWeather.getWeatherMain());
        TextView temp_minView = view.findViewById(R.id.temp_minView);
        TextView temp_maxView = view.findViewById(R.id.temp_maxView);

        TextView atmView = view.findViewById(R.id.atmView);
        TextView humidityView = view.findViewById(R.id.humidityView);
        TextView windView = view.findViewById(R.id.windView);

        TextView sunsireView = view.findViewById(R.id.sunsireView);
        TextView sunsetView = view.findViewById(R.id.sunsetView);*//*

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ScrollView)object);
    }*/




}
