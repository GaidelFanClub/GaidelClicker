package com.example.gfc.gaidelclicker.stat;


import com.example.gfc.gaidelclicker.GlobalPrefs;
import com.example.gfc.gaidelclicker.utils.FormatUtils;

import java.util.ArrayList;

/**
 * Created by Artem on 31.12.2016.
 */

public class StatRepository {
    private static StatRepository instance = new StatRepository();

    public static StatRepository getInstance() {
        return instance;
    }

    private ArrayList<String> stat;

    private StatRepository() {
        stat = new ArrayList<>();
    }

    public ArrayList<String> getStat() {
        stat.clear();
        stat.add("Текущий баланс: " + FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getBalance()));
        stat.add("Накликано за все время: " + GlobalPrefs.getInstance().getCountOfClicks());
        stat.add("Собрано вручную: " + FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getProfitFromClicks()));
        stat.add("Собрано за все время: " + FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getWholeProfit()));
        stat.add("Количество пойманных золотых Гайделей: " + GlobalPrefs.getInstance().getGoldenCookies());
        return stat;
    }
}
