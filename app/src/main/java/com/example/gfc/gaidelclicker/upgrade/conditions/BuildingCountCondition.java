package com.example.gfc.gaidelclicker.upgrade.conditions;

import com.example.gfc.gaidelclicker.building.Building;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;

/**
 * Created by user on 02.01.2017.
 */

public class BuildingCountCondition implements Condition {

    private Building building;
    private int needCount;

    public BuildingCountCondition(int buildId, int needCount) {
        this.building = BuildingsRepository.getInstance().getBuildingById(buildId);
        this.needCount = needCount;
    }

    @Override
    public boolean isFulfilled() {
        return building.getCount() >= needCount;
    }
}
