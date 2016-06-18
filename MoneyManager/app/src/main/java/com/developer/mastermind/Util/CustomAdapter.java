package com.developer.mastermind.Util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.developer.mastermind.moneymanager.R;

import java.util.ArrayList;

/**
 * Created by Dhananjay on 6/18/2016.
 */
public class CustomAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Activity mainActivity ;
    private ListModel tempValues = null;
    private ArrayList data;

    public CustomAdapter(Activity activity , ArrayList d)
    {
        this.mainActivity = activity;
        data = d;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (data.size()<=0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView name;
        TextView amount;
        Button addButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder ;
        View rowView = convertView;

        if (convertView == null)
        {
            rowView = inflater.inflate(R.layout.list_view_row , null);

            holder = new Holder();
            holder.name = (TextView) rowView.findViewById(R.id.name);
            holder.amount = (TextView) rowView.findViewById(R.id.total_amount);
            holder.addButton = (Button) rowView.findViewById(R.id.add);

            rowView.setTag(holder);
        }
        else
        {
            holder = (Holder) rowView.getTag();
        }

        tempValues = null;
        tempValues = (ListModel) data.get(position);

        holder.name.setText(tempValues.getName());
        holder.amount.setText(tempValues.getTotalAmount());

        return rowView;
    }
}
