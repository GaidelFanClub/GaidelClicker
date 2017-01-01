package com.example.gfc.gaidelclicker.building;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gfc.gaidelclicker.GlobalPrefs;
import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.utils.FormatUtils;

import java.math.BigDecimal;

/**
 * Created by user on 26.12.2016.
 */

public class BuildingHolder extends RecyclerView.ViewHolder implements GlobalPrefs.OnBalanceChangedListener {

    private TextView amount;
    private TextView cost;
    private TextView name;
    private ImageView image;

    private Building bonus;

    public BuildingHolder(View itemView, final OnBuildingClickListener onBonusClickListener) {
        super(itemView);
        amount = (TextView) itemView.findViewById(R.id.amount);
        cost = (TextView) itemView.findViewById(R.id.cost);
        image = (ImageView) itemView.findViewById(R.id.image);
        name = (TextView) itemView.findViewById(R.id.name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bonus != null) {
                    onBonusClickListener.onBuildingClick(bonus);
                }
            }
        });
    }

    public void bind(Building bonus) {
        this.bonus = bonus;
        amount.setText(String.valueOf(bonus.getCount()));
        image.setImageResource(bonus.getImageResourceId());
        cost.setText(cost.getContext().getString(R.string.cost_format, FormatUtils.formatDecimalAsInteger(bonus.getPrice())));
        name.setText(bonus.getName());
        GlobalPrefs.getInstance().registerListener(this);
    }

    @Override
    public void onBalanceChanged(BigDecimal currentBalance, BigDecimal wholeProfit) {
        if (bonus != null) {
            if (bonus.getPrice().compareTo(currentBalance) > 0) {
                image.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            } else {
                image.clearColorFilter();
            }
        }
    }
}
