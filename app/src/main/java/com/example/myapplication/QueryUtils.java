package com.example.myapplication;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    private QueryUtils() {
    }


    public static Weather fetchWeatherDate(String requestUrl){
        // Create URL object
        URL url = createUrl(requestUrl);

        //Perform HTTP request to the URL and receiver a JSON response back
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a weather
        Weather weather = extractFeatureFromJson(jsonResponse);

        //Return the weather
        return weather;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem building the URL", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        //If the URL is null, then return early.
        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000); /* milliseconds*/
            urlConnection.setConnectTimeout(15000); /* milliseconds*/
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }catch (IOException e){
            Log.e(LOG_TAG, "Problem retrieving the weather JSON results.", e);
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return  jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a  {@link Weather} objects that has been built up from
     * parsing the given JSON response.
     */
    private static Weather extractFeatureFromJson(String weatherJSON){
        // IF the JSON string is empty ot null, then return early
        if (TextUtils.isEmpty(weatherJSON)){
            return null;
        }



        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try{
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(weatherJSON);

            JSONArray weatherArray = baseJsonResponse.getJSONArray("weather");

            // Get weatherArray
            JSONObject weatherObject = weatherArray.getJSONObject(0);

             /* Get main, description, icon in weatherObject */

            // Extract the value for the key called "main"
            String weatherMain = weatherObject.getString("main");

            // Extract the value for the key called "description"
            String weatherDescription = weatherObject.getString("description");

            // Extract the value for the key called "icon"
            String weatherIcon = weatherObject.getString("icon");


            // Get mainObject
            JSONObject mainObject = baseJsonResponse.getJSONObject("main");

            double temperature = mainObject.getDouble("temp");
            double pressure = mainObject.getDouble("pressure");
            double humidity = mainObject.getDouble("humidity");
            double temp_min = mainObject.getDouble("temp_min");
            double temp_max = mainObject.getDouble("temp_max");


            //Get windObject
            JSONObject windObject = baseJsonResponse.getJSONObject("wind");

            double speed = windObject.getDouble("speed");


            //Get sysObject
            JSONObject sysObject = baseJsonResponse.getJSONObject("sys");

            String country = sysObject.getString("country");
            long sunsire = sysObject.getLong("sunrise");
            long sunset = sysObject.getLong("sunset");

            // Get cityID, cityName
            long cityID = baseJsonResponse.getLong("id");

            String cityName = baseJsonResponse.getString("name");


            return new Weather(weatherMain, weatherDescription, weatherIcon,
                    temperature, pressure, humidity, temp_min, temp_max,
                    speed,
                    country, sunsire, sunset,
                    cityID, cityName);

        }catch (JSONException e){
            Log.e("QuetyUtils", "Problem parsing the Weather JSON results", e);
        }
        return new Weather();
    }
}
