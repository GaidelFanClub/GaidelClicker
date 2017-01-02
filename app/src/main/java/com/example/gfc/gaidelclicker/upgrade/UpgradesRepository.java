package com.example.gfc.gaidelclicker.upgrade;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02.01.2017.
 */

public class UpgradesRepository {

    private static UpgradesRepository instance = new UpgradesRepository();

    public static UpgradesRepository getInstance() {
        return instance;
    }

    private List<Upgrade> allUpgrades;

    private UpgradesRepository() {
        allUpgrades = new ArrayList<>();
        allUpgrades.add(new SimpleMulUpgrade(0, "Первый апгрейд", "х2 профит от клика", BigInteger.valueOf(100), BigDecimal.valueOf(2), BuildingsRepository.ID_TAP));
        allUpgrades.add(new SimpleMulUpgrade(0, "Украсть ручку у Гайделя", "х2 профит от клика", BigInteger.valueOf(500), BigDecimal.valueOf(2), BuildingsRepository.ID_TAP));
        allUpgrades.add(new SimpleMulUpgrade(0, "Написать жалобу в деканат", "х2 профит от клика", BigInteger.valueOf(10000), BigDecimal.valueOf(2), BuildingsRepository.ID_TAP));
    }

    public List<Upgrade> getAllUpgrades() {
        return allUpgrades;
    }

    public List<Upgrade> getUnBoughtUpgrades() {
        List<Upgrade> unbought = new ArrayList<>();
        for (Upgrade upgrade : allUpgrades) {
            if (!upgrade.isBought()) {
                unbought.add(upgrade);
            }
        }
        return unbought;
    }
}
