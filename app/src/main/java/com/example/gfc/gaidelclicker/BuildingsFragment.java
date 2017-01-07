package com.example.gfc.gaidelclicker;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.building.Building;
import com.example.gfc.gaidelclicker.building.BuildingsAdapter;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.building.OnBuildingClickListener;

public class BuildingsFragment extends Fragment {

    private static final int COLUMN_COUNT = 2;
    private RecyclerView recyclerView;

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

    private void initRecycler(Context context) {
        GridLayoutManager layoutManager
                = new GridLayoutManager(context, COLUMN_COUNT);

        recyclerView.setLayoutManager(layoutManager);
        final BuildingsAdapter adapter = new BuildingsAdapter();
        adapter.setOnBuildingClickListener(new OnBuildingClickListener() {
            @Override
            public void onBuildingClick(Building bonus) {
                int res = GlobalPrefs.getInstance().getBalance().compareTo(bonus.getPrice());
                if (res != -1) {
                    GlobalPrefs.getInstance().changeBalance(bonus.getPrice().negate());
                    BuildingsRepository.getInstance().buy(bonus);
                    adapter.notifyDataSetChanged();
                    Analytics.getInstance().sendEvent("Buy building");
                }
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setData(BuildingsRepository.getInstance().getBuildings());
    }
}
