package com.example.gfc.gaidelclicker.upgrade;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.R;

import java.util.List;

/**
 * Created by user on 02.01.2017.
 */

public class UpgradesAdapter extends RecyclerView.Adapter<UpgradeHolder> {

    private List<Upgrade> data;
    private OnUpgradeClickListener onUpgradeClickListener;

    public void setData(List<Upgrade> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnUpgradeClickListener(OnUpgradeClickListener onUpgradeClickListener) {
        this.onUpgradeClickListener = onUpgradeClickListener;
    }

    @Override
    public UpgradeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upgrade, parent, false);
        return new UpgradeHolder(view, onUpgradeClickListener);
    }

    @Override
    public void onBindViewHolder(UpgradeHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
