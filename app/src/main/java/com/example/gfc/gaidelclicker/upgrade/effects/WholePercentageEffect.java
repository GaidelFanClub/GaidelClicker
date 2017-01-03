package com.example.gfc.gaidelclicker.upgrade.effects;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

/**
 * Created by user on 03.01.2017.
 */

public class WholePercentageEffect implements Effect {

    private int percentage;

    public WholePercentageEffect(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public void perform() {
        BuildingsRepository.getInstance().addPercentage(percentage);
    }
}
