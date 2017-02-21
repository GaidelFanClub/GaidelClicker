package com.example.gfc.gaidelclicker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.stat.StatRepository;
import com.example.gfc.gaidelclicker.stat.StatisticAdapter;

import java.math.BigDecimal;


/**
 * Created by Artem on 29.12.2016.
 */

public class StatisticFragment extends Fragment implements GlobalPrefs.OnBalanceChangedListener {

    private static final int COLUMN_COUNT = 1;
    private RecyclerView recyclerView;
    private StatisticAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_items, container, false);
        recyclerView = (RecyclerView) view;
        initRecycler(view.getContext());

        return view;
    }

    private void initRecycler(Context context) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, COLUMN_COUNT);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StatisticAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(StatRepository.getInstance().getStat());
    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalPrefs.getInstance().registerListener(this);
    }

    @Override
    public void onBalanceChanged(BigDecimal currentBalance, BigDecimal wholeProfit) {
        adapter.setData(StatRepository.getInstance().getStat());
    }
}
