package com.example.gfc.gaidelclicker.upgrade;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02.01.2017.
 */

public class UpgradesAdapter extends RecyclerView.Adapter<UpgradeHolder> {

    private List<Upgrade> data = new ArrayList<>();
    private OnUpgradeClickListener onUpgradeClickListener;

    public void setData(List<Upgrade> data) {
        if (isEquals(this.data, data)) return;
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    private boolean isEquals(List<Upgrade> old, List<Upgrade> current) {
        if (old == null) return false;
        if (old.size() != current.size()) return false;
        for (int i = 0; i < old.size(); i++) {
            if (old.get(i).getId() != current.get(i).getId()) return false;
        }
        return true;
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
