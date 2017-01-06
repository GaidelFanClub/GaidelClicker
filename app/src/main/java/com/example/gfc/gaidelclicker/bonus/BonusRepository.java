package com.example.gfc.gaidelclicker.bonus;

import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.utils.RandomUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 30.12.2016.
 */

public class BonusRepository {

    private static BonusRepository instance = new BonusRepository();

    public static BonusRepository getInstance() {
        return instance;
    }

    private Bonus[] bonuses;

    private BonusRepository() {
        List<Bonus> bonusesList = new ArrayList<>();
        bonusesList.add(new Bonus(1, R.mipmap.silver_gaidel, "", false, 77 * 1000, BigDecimal.valueOf(7d), BigDecimal.valueOf(7d), -1) {
            @Override
            public String getMessage() {
                int duration = 77 * BuildingsRepository.getInstance().getMultipleGoldenCookieEffectFactor();
                return "Прибыль увеличена в 7 раз на " + duration + " секунд";
            }
        });
        bonusesList.add(new Bonus(2, R.mipmap.gold_gaidel, "", false, 13 * 1000, BigDecimal.valueOf(777d), BigDecimal.ONE, -1) {
            @Override
            public String getMessage() {
                int duration = 13 * BuildingsRepository.getInstance().getMultipleGoldenCookieEffectFactor();
                return "Прибыль ЗА КЛИКИ увеличена в 777 раз на " + duration + " секунд";
            }
        });
        bonusesList.add(new PercentActiveBonus(3, R.mipmap.bronze_gaidel, 0.15, 0.2));
        bonuses = bonusesList.toArray(new Bonus[bonusesList.size()]);
    }

    public Bonus[] getBonuses() {
        return bonuses;
    }

    public Bonus getRandomBonus() {
        return bonuses[RandomUtils.nextIndex(bonuses.length)];
    }


}
