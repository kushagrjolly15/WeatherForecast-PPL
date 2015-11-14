package com.example.kushagrjolly.weather;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private final Activity context;
    private final ArrayList<String> times;
    private final ArrayList<String> maxTemps;
    private final ArrayList<String> icons;

    public CustomListAdapter(Activity context, ArrayList<String> times, ArrayList<String> maxTemps, ArrayList<String> icons) {
        // TODO Auto-generated constructor stub
        this.context=context;
        this.times = times;
        this.icons = icons;
        this.maxTemps = maxTemps;
    }

    @Override
    public int getCount() {
        return times.size();
    }

    @Override
    public Object getItem(int position) {
        return times.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,View view,ViewGroup parent) {

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mylist, null);
        }

        TextView time = (TextView) view.findViewById(R.id.time);
        TextView max = (TextView) view.findViewById(R.id.maxTemp);
        ImageView weatherImage = (ImageView) view.findViewById(R.id.weather_image);
        getWeatherIcon(weatherImage, icons.get(position));
        time.setText(times.get(position));
        max.setText(maxTemps.get(position));
        return view;

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
}
