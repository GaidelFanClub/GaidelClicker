package com.example.gfc.gaidelclicker;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.upgrade.OnUpgradeClickListener;
import com.example.gfc.gaidelclicker.upgrade.Upgrade;
import com.example.gfc.gaidelclicker.upgrade.UpgradesAdapter;
import com.example.gfc.gaidelclicker.upgrade.UpgradesRepository;
import com.google.android.gms.analytics.GoogleAnalytics;

import java.math.BigDecimal;

public class UpgradesFragment extends Fragment implements GlobalPrefs.OnBalanceChangedListener {

    private static final int COLUMN_COUNT = 1;
    private RecyclerView recyclerView;
    private UpgradesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_items, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        initRecycler(context);

        return view;
    }

    private void initRecycler(final Context context) {
        GridLayoutManager layoutManager
                = new GridLayoutManager(context, COLUMN_COUNT);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new UpgradesAdapter();
        adapter.setOnUpgradeClickListener(new OnUpgradeClickListener() {
            @Override
            public void onUpgradeClick(Upgrade upgrade) {
                BigDecimal decimalCost = new BigDecimal(upgrade.getCost().toString());
                if (decimalCost.compareTo(GlobalPrefs.getInstance().getBalance()) <= 0) {
                    GlobalPrefs.getInstance().changeBalance(decimalCost.negate());
                    upgrade.buy();
                    UpgradesRepository.getInstance().refresh();
                    BuildingsRepository.getInstance().recalculateDelta();
                    refresh();
                    Analytics.getInstance().sendEvent("Buy upgrade");
                }
            }
        });
        recyclerView.setAdapter(adapter);
        refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
        GlobalPrefs.getInstance().registerListener(this); // todo improve performance
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void refresh() {
        adapter.setData(UpgradesRepository.getInstance().getAvailableUpgrades());
    }

    @Override
    public void onBalanceChanged(BigDecimal currentBalance, BigDecimal wholeProfit) {
        refresh();
    }
}
