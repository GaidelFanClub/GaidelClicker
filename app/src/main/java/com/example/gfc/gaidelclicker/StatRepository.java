package com.example.gfc.gaidelclicker;


import com.example.gfc.gaidelclicker.StatRepository;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

import java.util.ArrayList;
import java.util.List;

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
        stat.add("Текущий баланс: " + GlobalPrefs.getInstance().getBalance());
        stat.add("Количество автокликов в секунду: "+ GlobalPrefs.getInstance().getWholeProfit());
        stat.add("Количество пойманных золотых Гайделей: " + GlobalPrefs.getInstance().getGoldenCookies());



    }

    public ArrayList<String> getStat() {
        return stat;
    }
}
