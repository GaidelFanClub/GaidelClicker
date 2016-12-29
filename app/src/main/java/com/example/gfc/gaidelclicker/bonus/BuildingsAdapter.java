package com.example.gfc.gaidelclicker.bonus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.Building;
import com.example.gfc.gaidelclicker.R;

/**
 * Created by user on 26.12.2016.
 */

public class BuildingsAdapter extends RecyclerView.Adapter<BuildingHolder> {

    private Building[] data;
    private OnBuildingClickListener onBuildingClickListener;

    public void setOnBuildingClickListener(OnBuildingClickListener onBuildingClickListener) {
        this.onBuildingClickListener = onBuildingClickListener;
    }

    public void setData(Building[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public BuildingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_building, parent, false);
        return new BuildingHolder(view, onBuildingClickListener);
    }

    @Override
    public void onBindViewHolder(BuildingHolder holder, int position) {
        holder.bind(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
