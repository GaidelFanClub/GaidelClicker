package com.example.gfc.gaidelclicker.upgrade;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gfc.gaidelclicker.GlobalPrefs;
import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.utils.FormatUtils;

import java.math.BigDecimal;

/**
 * Created by user on 02.01.2017.
 */

public class UpgradeHolder extends RecyclerView.ViewHolder implements GlobalPrefs.OnBalanceChangedListener {

    private View itemView;
    private ImageView icon;
    private TextView name;
    private TextView effect;
    private TextView cost;

    private Upgrade upgrade;

    public UpgradeHolder(View itemView, final OnUpgradeClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        icon = (ImageView) itemView.findViewById(R.id.icon);
        name = (TextView) itemView.findViewById(R.id.name);
        effect = (TextView) itemView.findViewById(R.id.effect);
        cost = (TextView) itemView.findViewById(R.id.cost);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upgrade != null) {
                    listener.onUpgradeClick(upgrade);
                }
            }
        });
    }

    public void bind(Upgrade upgrade) {
        this.upgrade = upgrade;
        icon.setImageResource(upgrade.getResourceId());
        name.setText(upgrade.getName());
        effect.setText(upgrade.getEffect());
        cost.setText("Цена: " + FormatUtils.formatInteger(upgrade.getCost()));
        GlobalPrefs.getInstance().registerListener(this);
    }

    @Override
    public void onBalanceChanged(BigDecimal currentBalance, BigDecimal wholeProfit) {
        if (upgrade != null) {
            float alpha = 1f;
            if (upgrade.getCostAsDecimal().compareTo(currentBalance) > 0) {
                alpha = 0.5f;
            }
            itemView.setAlpha(alpha);
        }
    }
}
