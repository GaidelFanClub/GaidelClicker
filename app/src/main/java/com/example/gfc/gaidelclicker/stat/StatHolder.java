package com.example.gfc.gaidelclicker.stat;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gfc.gaidelclicker.achievment.Achievement;
import com.example.gfc.gaidelclicker.R;

/**
 * Created by Artem on 31.12.2016.
 */

public class StatHolder extends RecyclerView.ViewHolder  {

    private TextView string;


    public StatHolder(View itemView) {
        super(itemView);
        string = (TextView) itemView.findViewById(R.id.text);

    }

    public void bind(String stat) {


    }
}
