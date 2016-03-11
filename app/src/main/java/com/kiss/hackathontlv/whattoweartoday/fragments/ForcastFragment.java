package com.kiss.hackathontlv.whattoweartoday.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kiss.hackathontlv.whattoweartoday.R;
import com.kiss.hackathontlv.whattoweartoday.adapters.ForcastAdapter;

/**
 * Created by erez on 11/03/16.
 */
public class ForcastFragment extends Fragment {
    View root;
    ListView forcastListView;
    ForcastAdapter forcastAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_forcast, container, false);
        forcastListView = (ListView) root.findViewById(R.id.ForcastListView);
        forcastAdapter = new ForcastAdapter(getContext(), 0);
        forcastListView.setAdapter(forcastAdapter);
        return root;
    }

    public void notifyDataSetChanged() {
        if (forcastAdapter != null)
            forcastAdapter.notifyDataSetChanged();
    }
}
