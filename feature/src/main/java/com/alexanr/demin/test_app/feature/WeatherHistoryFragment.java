package com.alexanr.demin.test_app.feature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeatherHistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private WeatherItemAdapter adapter;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_history_fragment, container, false);
        this.initRecycleView(view);
        if (this.adapter.getItemCount() != 0) {
            view.findViewById(R.id.recycler_view_layout).setVisibility(View.VISIBLE);
            view.findViewById(R.id.recycler_view_empty_layout).setVisibility(View.GONE);
        }
        this.bundle = getArguments();
        return view;
    }

    private void initRecycleView(View view) {
        this.recyclerView = view.findViewById(R.id.recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.adapter = new WeatherItemAdapter();
        this.adapter.setWeatherList(WeatherItems.getInstance().getItemList());
        this.recyclerView.setAdapter(adapter);
    }
}
