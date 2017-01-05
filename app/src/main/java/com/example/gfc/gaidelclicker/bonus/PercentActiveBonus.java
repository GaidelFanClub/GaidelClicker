package com.example.gfc.gaidelclicker.bonus;

import com.example.gfc.gaidelclicker.GlobalPrefs;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.utils.RandomUtils;

import java.math.BigDecimal;

/**
 * Created by Artem on 29.12.2016.
 */

public class PercentActiveBonus extends Bonus {

    private BigDecimal added;
    private BigDecimal fraction;

    public PercentActiveBonus(int id, int resourceId, double minPercent, double maxPercent) {
        super(id, resourceId, "", true, 0, null, null, -1);
        fraction = BigDecimal.valueOf(RandomUtils.nextDouble(minPercent, maxPercent));
    }

    @Override
    public void performImmediateAction(BigDecimal balance, BigDecimal wholeProfit) {
        added = balance.multiply(fraction);
        BigDecimal limit = BigDecimal.valueOf(60 * 60).multiply(BuildingsRepository.getInstance().getDeltaPerSecond());
        if (added.compareTo(limit) > 0) { //User can't rich more than one-hour profit
            added = limit;
        }
        GlobalPrefs.getInstance().changeBalance(added);
    }

    @Override
    public String getMessage() {
        added = added.setScale(3, BigDecimal.ROUND_HALF_EVEN);
        return "Добавлено " + added.toString();
    }
}
