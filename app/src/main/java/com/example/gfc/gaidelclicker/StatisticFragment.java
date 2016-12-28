package com.example.gfc.gaidelclicker;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Artem on 29.12.2016.
 */

public class StatisticFragment extends Fragment{
    TextView textView;
    private static final int COLUMN_COUNT = 2;
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stat, container, false);
        recyclerView = (RecyclerView) view;
        Context context = view.getContext();
        //textView = (TextView) view;
        initRecycler(context);

        return view;
    }
    private void initRecycler(Context context) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, COLUMN_COUNT);
        recyclerView.setLayoutManager(layoutManager);



    }
}
