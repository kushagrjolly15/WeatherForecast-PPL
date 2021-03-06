package com.example.kushagrjolly.weather;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter<String[]> {
    private List<String[]> scoreList = new ArrayList<String[]>();

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public String[] getItem(int index) {
        return this.scoreList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.day = (TextView) row.findViewById(R.id.day);
            viewHolder.max = (TextView) row.findViewById(R.id.max);
            viewHolder.min=(TextView)row.findViewById(R.id.min);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        String[] stat = getItem(position);
        viewHolder.day.setText(stat[0]);
        viewHolder.max.setText(stat[1] + " °C");
        viewHolder.min.setText(stat[2] + " °C");
        return row;
    }

    static class ItemViewHolder {
        TextView day;
        TextView max;
        TextView min;
    }
}
