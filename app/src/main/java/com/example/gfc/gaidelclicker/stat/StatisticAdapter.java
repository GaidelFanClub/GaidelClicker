package com.example.gfc.gaidelclicker.stat;

/**
 * Created by Artem on 31.12.2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.R;

import java.util.ArrayList;

public class StatisticAdapter extends RecyclerView.Adapter<StatHolder> {
    private ArrayList<String> data;

    public void setData(ArrayList<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public StatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stat, parent, false);
        return new StatHolder(view);
    }

    @Override
    public void onBindViewHolder(StatHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

