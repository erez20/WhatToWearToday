package com.kiss.hackathontlv.whattoweartoday.adapters;

import android.content.Context;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kiss.hackathontlv.whattoweartoday.Data.FiveDaysForcast;
import com.kiss.hackathontlv.whattoweartoday.Data.WhatToWearData;
import com.kiss.hackathontlv.whattoweartoday.R;

import java.util.List;

/**
 * Created by erez on 11/03/16.
 */
public class ForcastAdapter extends ArrayAdapter {
    Context context;
    private LayoutInflater inflater;

    public ForcastAdapter(Context context, int resource) {
        super(context, resource);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public ForcastAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        init(context);
    }

    public ForcastAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
        init(context);
    }

    public ForcastAdapter(Context context, int resource, int textViewResourceId, Object[] objects) {
        super(context, resource, textViewResourceId, objects);
        init(context);
    }

    public ForcastAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        init(context);
    }

    public ForcastAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        init(context);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.checklist_clothing_row,parent,false);
        }

        if (FiveDaysForcast.getInstance(null) != null) {
            WhatToWearData forcast = FiveDaysForcast.getInstance(null).moreDaysWear(position);
            if (forcast.getIsBoot())
                convertView.findViewById(R.id.bootsID).setVisibility(View.VISIBLE);
            if (forcast.getIsCoat())
                convertView.findViewById(R.id.coatID).setVisibility(View.VISIBLE);
            if (forcast.getIsHat())
                convertView.findViewById(R.id.hatID).setVisibility(View.VISIBLE);
            if (forcast.getIsScarf())
                convertView.findViewById(R.id.scarfID).setVisibility(View.VISIBLE);
            ((TextView)convertView.findViewById(R.id.temp_tv)).
                    setText(""+ (forcast.getMinTemp() + forcast.getMaxTemp())/2);
        }

        return convertView;


    }
}