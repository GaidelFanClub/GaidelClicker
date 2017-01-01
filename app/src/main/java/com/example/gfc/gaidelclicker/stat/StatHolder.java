package com.example.gfc.gaidelclicker.stat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gfc.gaidelclicker.R;

/**
 * Created by Artem on 31.12.2016.
 */

public class StatHolder extends RecyclerView.ViewHolder  {

    private TextView string;

    public StatHolder(View itemView) {
        super(itemView);
        string = (TextView) itemView.findViewById(R.id.name);

    }

    public void bind(String stat) {
        string.setText(stat);
    }
}
