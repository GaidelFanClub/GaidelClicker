package com.example.gfc.gaidelclicker.upgrade.effects;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

/**
 * Created by user on 03.01.2017.
 */

public class ClickCpsPercentEffect implements Effect {
    @Override
    public void perform() {
        BuildingsRepository.getInstance().increaseClickPercentCpS(1);
    }
}
