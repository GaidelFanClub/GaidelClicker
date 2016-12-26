package com.example.gfc.gaidelclicker.bonus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gfc.gaidelclicker.Bonus;
import com.example.gfc.gaidelclicker.R;

import java.util.List;

/**
 * Created by user on 26.12.2016.
 */

public class BonusesAdapter extends RecyclerView.Adapter<BonusHolder> {

    private List<Bonus> data;
    private OnBonusClickListener onBonusClickListener;

    public void setOnBonusClickListener(OnBonusClickListener onBonusClickListener) {
        this.onBonusClickListener = onBonusClickListener;
    }

    public void setData(List<Bonus> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public BonusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new BonusHolder(view, onBonusClickListener);
    }

    @Override
    public void onBindViewHolder(BonusHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
