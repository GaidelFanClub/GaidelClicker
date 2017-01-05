package com.example.gfc.gaidelclicker.upgrade.effects;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

/**
 * Created by user on 03.01.2017.
 */

public class GoldenBoostEffect implements Effect {

    private int divSpawn;
    private int mulPresent;
    private int mulEffect;

    public GoldenBoostEffect(int divSpawn, int mulPresent, int mulEffect) {
        this.divSpawn = divSpawn;
        this.mulPresent = mulPresent;
        this.mulEffect = mulEffect;
    }


    @Override
    public void perform() {
        BuildingsRepository.getInstance().changeGoldenCookieFactors(divSpawn, mulPresent, mulEffect);
    }
}
