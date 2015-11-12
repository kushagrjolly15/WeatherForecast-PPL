package com.example.kushagrjolly.weather;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.johnhiott.darkskyandroidlib.RequestBuilder;
import com.johnhiott.darkskyandroidlib.models.Request;
import com.johnhiott.darkskyandroidlib.models.WeatherResponse;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TabFragment1 extends Fragment {
    private static final String TAG = "MainActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment_1, container, false) ;
        final RequestBuilder weather = new RequestBuilder();
        final ListView listview = (ListView)rootView.findViewById(R.id.listview);
        final TextView maxt= (TextView) rootView.findViewById(R.id.textView);
        final TextView mint= (TextView) rootView.findViewById(R.id.textView1);
        final TextView summ= (TextView) rootView.findViewById(R.id.textView2);
        final ArrayList<String> time = new ArrayList<String>();
        final ArrayList<String> max = new ArrayList<String>();
        final ArrayList<String> min = new ArrayList<String>();
        Request request = new Request();
        request.setLat("26.61");
        request.setLng("77.23");
        request.setUnits(Request.Units.AUTO);
        request.setLanguage(Request.Language.ENGLISH);
        request.addExcludeBlock(Request.Block.CURRENTLY);
        request.removeExcludeBlock(Request.Block.CURRENTLY);

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
                summ.setVisibility(View.VISIBLE);
                CustomListAdapter adapter=new CustomListAdapter(getActivity(), time,max);
                listview.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d(TAG, "Error while calling: " + retrofitError.getUrl());
                Log.d(TAG, retrofitError.toString());
                CustomListAdapter adapter=new CustomListAdapter(getActivity(), time,max);
                listview.setAdapter(adapter);
            }
        });



        return rootView;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);



    }
}
