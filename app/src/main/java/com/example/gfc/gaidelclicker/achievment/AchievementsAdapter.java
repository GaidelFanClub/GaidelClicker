package com.example.gfc.gaidelclicker.achievment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.Building;
import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.bonus.BuildingHolder;
import com.example.gfc.gaidelclicker.bonus.OnBuildingClickListener;

/**
 * Created by user on 26.12.2016.
 */

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementHolder> {

    private Achievement[] data;

    public void setData(Achievement[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public AchievementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_achievement, parent, false);
        return new AchievementHolder(view);
    }

    @Override
    public void onBindViewHolder(AchievementHolder holder, int position) {
        holder.bind(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
