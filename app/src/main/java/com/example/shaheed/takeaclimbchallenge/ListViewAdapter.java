package com.example.shaheed.takeaclimbchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by shaheed on 4/10/18.
 */

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Medicine> medicineList = null;
    private ArrayList<Medicine> arraylist;

    public ListViewAdapter(Context context, List<Medicine> medicineList) {
        mContext = context;
        this.medicineList = medicineList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(medicineList);
    }

    public class ViewHolder{
        TextView name;
    }

    @Override
    public int getCount() {
        return medicineList.size();
    }

    @Override
    public Object getItem(int position) {
        return medicineList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_layout, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) convertView.findViewById(R.id.medName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(medicineList.get(position).getName());
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        medicineList.clear();
        if (charText.length() == 0) {
            medicineList.addAll(arraylist);
        } else {
            for (Medicine wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    medicineList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}