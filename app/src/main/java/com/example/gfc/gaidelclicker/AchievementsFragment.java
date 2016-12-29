package com.example.gfc.gaidelclicker;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.achievment.AchievementsAdapter;
import com.example.gfc.gaidelclicker.achievment.AchievementsRepository;
import com.example.gfc.gaidelclicker.event.AchievementUnlockedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AchievementsFragment extends Fragment {

    private static final int COLUMN_COUNT = 1;
    private RecyclerView recyclerView;
    private AchievementsAdapter adapter;

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

        adapter = new AchievementsAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(AchievementsRepository.getInstance().getAchievements());
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAchievementUnlocked(AchievementUnlockedEvent event) {
        adapter.notifyDataSetChanged();
    }
}
