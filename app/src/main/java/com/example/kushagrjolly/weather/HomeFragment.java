package com.example.kushagrjolly.weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.johnhiott.darkskyandroidlib.RequestBuilder;
import com.johnhiott.darkskyandroidlib.models.Request;
import com.johnhiott.darkskyandroidlib.models.WeatherResponse;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeFragment extends Fragment {
    private static final String TAG = "MainActivity";
    public ProgressDialog progressDialog;
    FragmentActivity activity;
    private TextView summ;
    private TextView mint;
    private TextView maxt;
    private ListView listview;
    private ImageView weatherImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        final RequestBuilder weather = new RequestBuilder();
        listview = (ListView) rootView.findViewById(R.id.listview);
        weatherImage = (ImageView) rootView.findViewById(R.id.weather_image);
        maxt = (TextView) rootView.findViewById(R.id.maxTemp);
        mint = (TextView) rootView.findViewById(R.id.minTemp);
        summ = (TextView) rootView.findViewById(R.id.summary);
        final ArrayList<String> time = new ArrayList<String>();
        final ArrayList<String> max = new ArrayList<String>();
        Request request = new Request();
        request.setLat("26.61");
        request.setLng("77.23");
        request.setUnits(Request.Units.AUTO);
        request.setLanguage(Request.Language.ENGLISH);
        request.addExcludeBlock(Request.Block.CURRENTLY);
        request.removeExcludeBlock(Request.Block.CURRENTLY);
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
                }
                maxt.setText(String.valueOf(weatherResponse.getDaily().getData().get(0).getTemperatureMax()));
                mint.setText(String.valueOf(weatherResponse.getDaily().getData().get(0).getTemperatureMin()));
                summ.setText(weatherResponse.getDaily().getData().get(0).getSummary());
                getWeatherIcon(weatherImage, weatherResponse.getCurrently().getIcon());
                summ.setVisibility(View.VISIBLE);
                CustomListAdapter adapter=new CustomListAdapter(getActivity(), time,max);
                listview.setAdapter(adapter);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "Error while calling: " + retrofitError.getUrl());
                Log.d(TAG, retrofitError.toString());
                CustomListAdapter adapter=new CustomListAdapter(getActivity(), time,max);
                listview.setAdapter(adapter);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
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
        } else if (iconType.equalsIgnoreCase("partly-cloudy-day") || iconType.equalsIgnoreCase("partly-cloudy-night")) {
            image.setImageResource(R.drawable.weather_condition_partly_cloudy);
        } else {
            image.setImageResource(R.drawable.weather_condition_sunny);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);



    }
}
