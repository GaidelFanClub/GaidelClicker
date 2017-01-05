package com.example.gfc.gaidelclicker.achievment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gfc.gaidelclicker.R;

/**
 * Created by user on 29.12.2016.
 */

public class AchievementHolder extends RecyclerView.ViewHolder {

    private ImageView icon;
    private TextView name;
    private TextView description;

    public AchievementHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        name = (TextView) itemView.findViewById(R.id.name);
        description = (TextView) itemView.findViewById(R.id.description);
    }

    public void bind(Achievement achievement) {
        icon.setImageResource(achievement.getImageResourceId());
        name.setText(achievement.getName());
        if (achievement.isUnlocked()) {
            if (achievement.getColor() != -1) {
                icon.setColorFilter(achievement.getColor(), PorterDuff.Mode.SRC_IN);
            } else {
                icon.clearColorFilter();
            }
        } else {
            icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        }
        description.setText(achievement.getDescription());
    }
}
