package com.example.myapplication;

/*POJO class Weather*/

public class Weather {

    // JSON root.weather
    private String weatherMain;
    private String weatherDescription;
    private String weatherIcon;

    //JSON root.main
    private double temp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;

    //JSON root.wind
    private double windSpeed;
    /*//JSON root.rain
    private String rainPrecipi;

    //JSON root.snow

    private String snowPrecipi;*/
    //JSON root.sys
    private String country;
    private long sunrise;
    private long sunset;
    //JSON cityID, city name,
    private long cityID;
    private String cityName;

    public Weather(){

    }


    public Weather(String weatherMain, String weatherDescription, String weatherIcon,
                   double temp, double pressure, double humidity, double temp_min, double temp_max,
                   double windSpeed,
                   String country, long sunrise, long sunset,
                   long cityID, String cityName) {
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.windSpeed = windSpeed;

        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.cityID = cityID;
        this.cityName = cityName;
    }

    // getters - setters
    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public long getCityID() {
        return cityID;
    }

    public void setCityID(long cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }




}
