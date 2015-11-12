package com.example.kushagrjolly.weather;


        import android.app.Activity;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;
        import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<String> imgid;

    public CustomListAdapter(Activity context, ArrayList<String> itemname, ArrayList<String> imgid) {
        // TODO Auto-generated constructor stub
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    @Override
    public int getCount() {
        return itemname.size();
    }

    @Override
    public Object getItem(int position) {
        return itemname.get(position);
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

        TextView time = (TextView) view.findViewById(R.id.Itemname1);

        TextView max = (TextView) view.findViewById(R.id.Itemname2);

        time.setText(itemname.get(position));
        max.setText(imgid.get(position));
        return view;

    };
}
