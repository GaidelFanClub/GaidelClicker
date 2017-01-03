package com.example.gfc.gaidelclicker.upgrade.effects;

import com.example.gfc.gaidelclicker.building.Building;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;

import java.math.BigDecimal;

/**
 * Created by user on 02.01.2017.
 */

public class ClickFinalAddEffect implements Effect {

    private BigDecimal value;

    public ClickFinalAddEffect(BigDecimal value) {
        this.value = value;
    }

    @Override
    public void perform() {
        int nonCursorObjectsCount = 0;
        for (Building building : BuildingsRepository.getInstance().getBuildings()) {
            if (building.getId() == BuildingsRepository.ID_CLICK) continue;
            nonCursorObjectsCount += building.getCount();
        }
        BigDecimal resultBonus = value.multiply(BigDecimal.valueOf(nonCursorObjectsCount));
        BuildingsRepository.getInstance().changeFinalAddBonus(BuildingsRepository.ID_TAP, resultBonus);
    }
}
