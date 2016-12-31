package com.example.gfc.gaidelclicker;


import com.example.gfc.gaidelclicker.StatRepository;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.utils.FormatUtils;
import com.tumblr.remember.Remember;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * Created by Artem on 31.12.2016.
 */

public class StatRepository {
    private static StatRepository instance = new StatRepository();

    public static StatRepository getInstance() {
        return instance;
    }

    private static final String COUNT_OF_CLICK = "COUNT_OF_CLICK";

    private ArrayList<String> stat;

    private StatRepository() {
        stat = new ArrayList<>();
        stat.add("Текущий баланс: " + GlobalPrefs.getInstance().getBalance());
        stat.add("Количество автокликов в секунду: "+ GlobalPrefs.getInstance().getWholeProfit());
        stat.add("Количество пойманных золотых Гайделей: " + GlobalPrefs.getInstance().getGoldenCookies());



    }

    public ArrayList<String> getStat() {
        stat.clear();
        stat = new ArrayList<>();
        stat.add("Текущий баланс: " + FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getBalance()));
        stat.add("Накликано за все время: "+ FormatUtils.formatDecimalAsInteger(new BigDecimal(Remember.getString(COUNT_OF_CLICK, ""))));
        stat.add("Собрано за все время: "+ FormatUtils.formatDecimalAsInteger(GlobalPrefs.getInstance().getWholeProfit()));
        stat.add("Количество пойманных золотых Гайделей: " + GlobalPrefs.getInstance().getGoldenCookies());
        return stat;
    }
}
