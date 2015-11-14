package com.example.kushagrjolly.weather;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PredictionFragment extends Fragment {
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.prediction_fragment, container, false);
        listView = (ListView)rootView. findViewById(R.id.listView);
        listView.setDividerHeight(0);
        itemArrayAdapter = new ItemArrayAdapter(getActivity(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();
        scoreList.remove(0);
        for(int i=0;i<scoreList.size();i++){
            //Log.d("List:", String.valueOf(scoreList.get(i)));
            String []data=scoreList.get(i);
        }
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        int x= calendar.get(Calendar.DAY_OF_YEAR);
        long time= System.currentTimeMillis();
        for(String[] scoreData:scoreList ) {
            scoreData[0]=scoreData[0].substring(1, scoreData[0].length() - 1);
            double max= Double.parseDouble(scoreData[1]);
            max = ((max - 32)*5)/9;
            scoreData[1]=String.format("%.2f", max);
            if(!scoreData[2].contains("NA")) {
                double min = Double.parseDouble(scoreData[2]);
                min = ((min - 32) * 5) / 9;
                scoreData[2] = String.format("%.2f", min);
            }
            int y= Integer.parseInt(scoreData[0]);
            if(y>=x && y<=(x+7)){
                calendar.setTimeInMillis(time);
                scoreData[0]=formatter.format(calendar.getTime());
                time+=86400000;
                itemArrayAdapter.add(scoreData);
            }

        }
        return rootView;
    }
}