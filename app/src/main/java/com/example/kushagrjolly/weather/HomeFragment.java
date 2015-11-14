package com.example.kushagrjolly.weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.johnhiott.darkskyandroidlib.RequestBuilder;
import com.johnhiott.darkskyandroidlib.models.Request;
import com.johnhiott.darkskyandroidlib.models.WeatherResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "MainActivity";
    public ProgressDialog progressDialog;
    FragmentActivity activity;
    private TextView summ;
    private TextView mint;
    private TextView maxt;
    private RelativeLayout errorLayout;
    private RelativeLayout noErrorLayout;
    private ProgressBar imageProgress;
    private ListView listview;
    private ImageView weatherImage;
    private ArrayList<String> time;
    private ArrayList<String> max;
    private ArrayList<String> icons;
    private Forecast forecast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        final RequestBuilder weather = new RequestBuilder();
        listview = (ListView) rootView.findViewById(R.id.listview);
        weatherImage = (ImageView) rootView.findViewById(R.id.weather_image);
        maxt = (TextView) rootView.findViewById(R.id.maxTemp);
        mint = (TextView) rootView.findViewById(R.id.minTemp);
        summ = (TextView) rootView.findViewById(R.id.summary);
        imageProgress = (ProgressBar) rootView.findViewById(R.id.image_progress);
        errorLayout = (RelativeLayout) rootView.findViewById(R.id.error_layout);
        noErrorLayout = (RelativeLayout) rootView.findViewById(R.id.no_error_layout);
        time = new ArrayList<String>();
        max = new ArrayList<String>();
        icons = new ArrayList<String>();

        weatherImage.setVisibility(View.GONE);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.forecast.io/forecast/397a20e0d8f404c0073a8db6108ccaf3")
                .build();
        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        ForecastApi forecastApi = restAdapter.create(ForecastApi.class);
        forecastApi.getDistance(new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {

                    reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

                    String line;

                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String result = sb.toString();
                forecast = getWeatherForecast(result);
                WekaClassifier classifier = new WekaClassifier();
                getWeatherIcon(weatherImage, classifier.wekaClassifier(forecast));
                weatherImage.setVisibility(View.VISIBLE);
                imageProgress.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Retrofit_Error", error.getKind().toString());
            }
        });

        Request request = new Request();

        //coordinates of Delhi
        request.setLat("26.61");
        request.setLng("77.23");

        request.setUnits(Request.Units.AUTO);
        request.setLanguage(Request.Language.ENGLISH);
        request.addExcludeBlock(Request.Block.CURRENTLY);
        request.removeExcludeBlock(Request.Block.CURRENTLY);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.redfort);
        Bitmap blurredBackground = BlurBuilder.blur(getActivity(), icon);
        ImageView background = (ImageView) rootView.findViewById(R.id.background);
        background.setImageBitmap(blurredBackground);
        showProgressBar();

        weather.getWeather(request, new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {

                for (int i = 0; i < weatherResponse.getHourly().getData().size() - 24; i++) {
                    long millis = weatherResponse.getHourly().getData().get(i).getTime();
                    long s = millis % 60;
                    long m = (millis / 60) % 60;
                    long h = (millis / (60 * 60)) % 24;
                    String hms = String.format("%d:%02d:%02d", h, m, s);
                    time.add(hms);
                    max.add(String.valueOf(weatherResponse.getHourly().getData().get(i).getApparentTemperature()));
                    icons.add(weatherResponse.getHourly().getData().get(i).getIcon());
                }
                maxt.setText(String.valueOf(weatherResponse.getCurrently().getApparentTemperature()) + " °C");
                mint.setText(String.valueOf(weatherResponse.getCurrently().getTemperature()) + " °C");
                summ.setText(weatherResponse.getDaily().getData().get(0).getSummary());
                summ.setVisibility(View.VISIBLE);
                CustomListAdapter adapter = new CustomListAdapter(getActivity(), time, max, icons);
                listview.setAdapter(adapter);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "Error while calling: " + retrofitError.getUrl());
                Log.d(TAG, retrofitError.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                errorLayout.setVisibility(View.VISIBLE);
                noErrorLayout.setVisibility(View.GONE);
            }
        });



        return rootView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    public void showProgressBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    //convert weather response to usable data
    private Forecast getWeatherForecast(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject currently = jsonObject.getJSONObject("currently");
            Forecast forecast = new Forecast();
            forecast.setPrecipIntensity(currently.getDouble("precipIntensity"));
            forecast.setPrecipProbability(currently.getDouble("precipProbability"));
            forecast.setDewPoint(currently.getDouble("dewPoint"));
            forecast.setHumidity(currently.getDouble("humidity"));
            forecast.setWindSpeed(currently.getDouble("windSpeed"));
            forecast.setWindBearing(currently.getDouble("windBearing"));
            if (response.contains("visibility")) {
                forecast.setVisibility(currently.getDouble("visibility"));
            } else {
                forecast.setVisibility(0);
            }
            forecast.setCloudCover(currently.getDouble("cloudCover"));
            forecast.setPressure(currently.getDouble("pressure"));
            forecast.setOzone(currently.getDouble("ozone"));
            forecast.setIcon(currently.getString("icon"));
            if (currently.toString().contains("precipType")) {
                String precipType = currently.getString("precipType");
                forecast.setPrecipType(precipType);
            } else {
                forecast.setPrecipType("");
            }
            return forecast;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getWeatherIcon(ImageView image, String iconType) {
        if (iconType.equalsIgnoreCase("clear-day") || iconType.equalsIgnoreCase("clear-night")) {
            image.setImageResource(R.drawable.weather_condition_sunny);
        } else if (iconType.equalsIgnoreCase("rain") || iconType.equalsIgnoreCase("sleet")) {
            image.setImageResource(R.drawable.weather_condition_rain);
        } else if (iconType.equalsIgnoreCase("snow")) {
            image.setImageResource(R.drawable.weather_condition_snow);
        } else if (iconType.equalsIgnoreCase("wind")) {
            image.setImageResource(R.drawable.weather_condition_windy);
        } else if (iconType.equalsIgnoreCase("fog")) {
            image.setImageResource(R.drawable.weather_condition_fog);
        } else if (iconType.equalsIgnoreCase("cloudy")) {
            image.setImageResource(R.drawable.weather_condition_cloudy);
        } else if (iconType.equalsIgnoreCase("partly-cloudy-day") || iconType.equalsIgnoreCase("partly-cloudy-night") || iconType.equalsIgnoreCase("partly-cloudy")) {
            image.setImageResource(R.drawable.weather_condition_partly_cloudy);
        } else {
            image.setImageResource(R.drawable.weather_condition_sunny);
        }
    }

    public static class BlurBuilder {
        private static final float BITMAP_SCALE = 0.4f;
        private static final float BLUR_RADIUS = 1.5f;

        public static Bitmap blur(Context context, Bitmap image) {
            int width = Math.round(image.getWidth() * BITMAP_SCALE);
            int height = Math.round(image.getHeight() * BITMAP_SCALE);

            Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
            Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

            RenderScript rs = RenderScript.create(context);
            //class used to blur
            ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            //creates input memory allocation in renderscript kernel
            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
            //creates output memory allocation from renderscript kernel
            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
            theIntrinsic.setRadius(BLUR_RADIUS);
            theIntrinsic.setInput(tmpIn);
            theIntrinsic.forEach(tmpOut);
            tmpOut.copyTo(outputBitmap);

            return outputBitmap;
        }
    }


}
