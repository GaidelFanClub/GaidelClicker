package com.example.gfc.gaidelclicker.upgrade;

import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.upgrade.conditions.BuildingCountCondition;
import com.example.gfc.gaidelclicker.upgrade.effects.ClickFinalAddEffect;
import com.example.gfc.gaidelclicker.upgrade.effects.SimpleAddEffect;
import com.example.gfc.gaidelclicker.upgrade.effects.SimpleMulEffect;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import static com.example.gfc.gaidelclicker.building.BuildingsRepository.*;

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

        /*Click & clicker*/
        allUpgrades.add(new Upgrade(1, R.mipmap.click, "Усиление пальца", "х2 профит от клика и кликера", BigInteger.valueOf(100))
                .addCondition(new BuildingCountCondition(ID_CLICK, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), BuildingsRepository.ID_TAP, BuildingsRepository.ID_CLICK)));
        allUpgrades.add(new Upgrade(2, R.mipmap.click, "Крем для рук", "х2 профит от клика и кликера", BigInteger.valueOf(500))
                .addCondition(new BuildingCountCondition(ID_CLICK, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), BuildingsRepository.ID_TAP, BuildingsRepository.ID_CLICK)));
        allUpgrades.add(new Upgrade(3, R.mipmap.click, "Усиление пальца", "х2 профит от клика и кликера", BigInteger.valueOf(10000))
                .addCondition(new BuildingCountCondition(ID_CLICK, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), BuildingsRepository.ID_TAP, BuildingsRepository.ID_CLICK)));
        allUpgrades.add(new Upgrade(4, R.mipmap.click, "Тысяча тапов", "+0.1 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(500000))
                .addCondition(new BuildingCountCondition(ID_CLICK, 20))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(0.1d))));
        allUpgrades.add(new Upgrade(5, R.mipmap.click, "Миллион тапов", "+0.5 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(50000000))
                .addCondition(new BuildingCountCondition(ID_CLICK, 40))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(0.5d))));
        allUpgrades.add(new Upgrade(6, R.mipmap.click, "Миллиард тапов", "+2 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(500000000))
                .addCondition(new BuildingCountCondition(ID_CLICK, 80))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(2d))));
        allUpgrades.add(new Upgrade(7, R.mipmap.click, "Триллион тапов", "+10 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(5000000000L))
                .addCondition(new BuildingCountCondition(ID_CLICK, 120))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(10d))));
        allUpgrades.add(new Upgrade(8, R.mipmap.click, "Квадриллион тапов", "+20 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(50000000000L))
                .addCondition(new BuildingCountCondition(ID_CLICK, 160))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(20d))));
        allUpgrades.add(new Upgrade(9, R.mipmap.click, "Квинтиллин тапов", "+100 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(50000000000000L))
                .addCondition(new BuildingCountCondition(ID_CLICK, 200))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(100d))));
        allUpgrades.add(new Upgrade(10, R.mipmap.click, "Секстиллион тапов", "+200 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(500000000000000L))
                .addCondition(new BuildingCountCondition(ID_CLICK, 240))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(200d))));
        allUpgrades.add(new Upgrade(11, R.mipmap.click, "Септиллион тапов", "+400 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(5000000000000000L))
                .addCondition(new BuildingCountCondition(ID_CLICK, 280))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(400d))));
        allUpgrades.add(new Upgrade(12, R.mipmap.click, "Октиллион тапов", "+800 к клику и кликеру за каждый объект некликр", BigInteger.valueOf(50000000000000000L))
                .addCondition(new BuildingCountCondition(ID_CLICK, 320))
                .addEffect(new ClickFinalAddEffect(BigDecimal.valueOf(800d))));

        /*Twitch */
        allUpgrades.add(new Upgrade(13, R.mipmap.twitch, "Зарегистрироваться на твиче", "+0.3 за просмотр твича", BigInteger.valueOf(1000))
                .addCondition(new BuildingCountCondition(ID_TWITCH, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(0.3), ID_TWITCH)));
        allUpgrades.add(new Upgrade(14, R.mipmap.twitch, "Зафоловить Гайделя", "х2 за просмотр твича", BigInteger.valueOf(10000))
                .addCondition(new BuildingCountCondition(ID_TWITCH, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TWITCH)));
        allUpgrades.add(new Upgrade(15, R.mipmap.twitch, "Включить звук", "х2 за просмотр твича", BigInteger.valueOf(100_000))
                .addCondition(new BuildingCountCondition(ID_TWITCH, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TWITCH)));
        allUpgrades.add(new Upgrade(16, R.mipmap.twitch, "Подписаться на канал", "х2 за просмотр твича", BigInteger.valueOf(5_000_000))
                .addCondition(new BuildingCountCondition(ID_TWITCH, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TWITCH)));
        allUpgrades.add(new Upgrade(17, R.mipmap.twitch, "Задонатить тридцатку", "х2 за просмотр твича", BigInteger.valueOf(100_000_000L))
                .addCondition(new BuildingCountCondition(ID_TWITCH, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TWITCH)));
        allUpgrades.add(new Upgrade(18, R.mipmap.twitch, "Задонатить косарь", "х2 за просмотр твича", BigInteger.valueOf(800_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TWITCH, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TWITCH)));

        /*Lecture */
        allUpgrades.add(new Upgrade(19, R.mipmap.lecture, "Сесть на первую парту", "+1 за посещение лекции", BigInteger.valueOf(5000))
                .addCondition(new BuildingCountCondition(ID_LECTURE, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(1), ID_LECTURE)));
        allUpgrades.add(new Upgrade(20, R.mipmap.lecture, "Делать вид что умный", "х2 за посещение лекции", BigInteger.valueOf(50_000))
                .addCondition(new BuildingCountCondition(ID_LECTURE, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LECTURE)));
        allUpgrades.add(new Upgrade(21, R.mipmap.lecture, "Смеяться как конь над шутками Гайделя", "х2 за посещение лекции", BigInteger.valueOf(500_000))
                .addCondition(new BuildingCountCondition(ID_LECTURE, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LECTURE)));
        allUpgrades.add(new Upgrade(22, R.mipmap.lecture, "Записывать лекции", "х2 за посещение лекции", BigInteger.valueOf(25_000_000))
                .addCondition(new BuildingCountCondition(ID_LECTURE, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LECTURE)));
        allUpgrades.add(new Upgrade(23, R.mipmap.lecture, "Убрать телефон за спину соседа спереди", "х2 за посещение лекции", BigInteger.valueOf(500_000_000L))
                .addCondition(new BuildingCountCondition(ID_LECTURE, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LECTURE)));
        allUpgrades.add(new Upgrade(24, R.mipmap.lecture, "Одевать костюм как Гайдель", "х2 за посещение лекции", BigInteger.valueOf(4_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_LECTURE, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LECTURE)));

        /*LabWork*/

        allUpgrades.add(new Upgrade(25, R.mipmap.lab_work, "Cделать лабу", "+4 за сдачу лабы", BigInteger.valueOf(30_000))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(4), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(26, R.mipmap.lab_work, "Узнать как работает лаба", "х2 за сдачу лабы", BigInteger.valueOf(300_000))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(27, R.mipmap.lab_work, "Ответить на вопросы", "х2 за сдачу лабы", BigInteger.valueOf(3_000_000))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(28, R.mipmap.lab_work, "Сделать отчет по лабе", "х2 за сдачу лабы", BigInteger.valueOf(150_000_000L))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(29, R.mipmap.lab_work, "ХХХ", "х2 за сдачу лабы", BigInteger.valueOf(3_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(30, R.mipmap.lab_work, "ХХХ", "х2 за сдачу лабы", BigInteger.valueOf(24_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));


        /*Practice*/
        allUpgrades.add(new Upgrade(31, R.mipmap.practice, "Cделать лабу", "+10 за сдачу идз", BigInteger.valueOf(100_000))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(10), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(32, R.mipmap.practice, "Узнать как работает лаба", "х2 за сдачу идз", BigInteger.valueOf(1_000_000))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(33, R.mipmap.practice, "Ответить на вопросы", "х2 за сдачу идз", BigInteger.valueOf(10_000_000))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(34, R.mipmap.practice, "Сделать отчет по лабе", "х2 за сдачу идз", BigInteger.valueOf(500_000_000L))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(35, R.mipmap.practice, "ХХХ", "х2 за сдачу идз", BigInteger.valueOf(10_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(36, R.mipmap.practice, "ХХХ", "х2 за сдачу идз", BigInteger.valueOf(80_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));

        /*Cirlce*/


        allUpgrades.add(new Upgrade(37, R.mipmap.circle, "Cделать лабу", "+30 за посещение кружка", BigInteger.valueOf(400_000))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(30), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(38, R.mipmap.circle, "Узнать как работает лаба", "х2 за посещение кружка", BigInteger.valueOf(4_000_000))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(39, R.mipmap.circle, "Ответить на вопросы", "х2 за посещение кружка", BigInteger.valueOf(40_000_000))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(40, R.mipmap.circle, "Сделать отчет по лабе", "х2 за посещение кружка", BigInteger.valueOf(2_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(41, R.mipmap.circle, "ХХХ", "х2 за посещение кружка", BigInteger.valueOf(40_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(42, R.mipmap.circle, "ХХХ", "х2 за посещение кружка", BigInteger.valueOf(320_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));

        /*Cormen*/
        allUpgrades.add(new Upgrade(43, R.mipmap.cormen, "Cделать лабу", "+100 за чтение Кормена", BigInteger.valueOf(2_000_000))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(100), ID_CORMEN)));
        allUpgrades.add(new Upgrade(44, R.mipmap.cormen, "Узнать как работает лаба", "х2 за чтение Кормена", BigInteger.valueOf(4_000_000))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));
        allUpgrades.add(new Upgrade(45, R.mipmap.cormen, "Ответить на вопросы", "х2 за чтение Кормена", BigInteger.valueOf(200_000_000))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));
        allUpgrades.add(new Upgrade(46, R.mipmap.cormen, "Сделать отчет по лабе", "х2 за чтение Кормена", BigInteger.valueOf(10_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));
        allUpgrades.add(new Upgrade(47, R.mipmap.cormen, "ХХХ", "х2 за чтение Кормена", BigInteger.valueOf(200_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));
        allUpgrades.add(new Upgrade(48, R.mipmap.cormen, "ХХХ", "х2 за чтение Кормена", BigInteger.valueOf(1_600_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));

        /*Timus*/
        allUpgrades.add(new Upgrade(49, R.mipmap.task, "Cделать лабу", "+1666 за решение Тимуса", BigInteger.valueOf(15_000_000))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(1666), ID_TIMUS)));
        allUpgrades.add(new Upgrade(50, R.mipmap.task, "Узнать как работает лаба", "х2 за решение Тимуса", BigInteger.valueOf(75_000_000))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));
        allUpgrades.add(new Upgrade(51, R.mipmap.task, "Ответить на вопросы", "х2 за решение Тимуса", BigInteger.valueOf(1_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));
        allUpgrades.add(new Upgrade(52, R.mipmap.task, "Сделать отчет по лабе", "х2 за решение Тимуса", BigInteger.valueOf(25_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));
        allUpgrades.add(new Upgrade(53, R.mipmap.task, "ХХХ", "х2 за решение Тимуса", BigInteger.valueOf(1_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));
        allUpgrades.add(new Upgrade(54, R.mipmap.task, "ХХХ", "х2 за решение Тимуса", BigInteger.valueOf(8_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));

        /*CF*/
        allUpgrades.add(new Upgrade(55, R.mipmap.code_forces, "Cделать лабу", "+9999 за решение Тимуса", BigInteger.valueOf(1_000_000_000))
                .addCondition(new BuildingCountCondition(ID_CF, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(9999), ID_CF)));
        allUpgrades.add(new Upgrade(56, R.mipmap.code_forces, "Узнать как работает лаба", "х2 за решение Тимуса", BigInteger.valueOf(9_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));
        allUpgrades.add(new Upgrade(57, R.mipmap.code_forces, "Ответить на вопросы", "х2 за решение Тимуса", BigInteger.valueOf(99_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));
        allUpgrades.add(new Upgrade(58, R.mipmap.code_forces, "Сделать отчет по лабе", "х2 за решение Тимуса", BigInteger.valueOf(1_234_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));
        allUpgrades.add(new Upgrade(59, R.mipmap.code_forces, "ХХХ", "х2 за решение Тимуса", BigInteger.valueOf(123_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));
        allUpgrades.add(new Upgrade(60, R.mipmap.code_forces, "ХХХ", "х2 за решение Тимуса", BigInteger.valueOf(987_654_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));

        /*BEER*/
        allUpgrades.add(new Upgrade(61, R.mipmap.beer_factory, "Cделать лабу", "+999999 за решение Тимуса", BigInteger.valueOf(40_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(999999), ID_BEER)));
        allUpgrades.add(new Upgrade(62, R.mipmap.beer_factory, "Узнать как работает лаба", "х2 за решение Тимуса", BigInteger.valueOf(400_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));
        allUpgrades.add(new Upgrade(63, R.mipmap.beer_factory, "Ответить на вопросы", "х2 за решение Тимуса", BigInteger.valueOf(4_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));
        allUpgrades.add(new Upgrade(64, R.mipmap.beer_factory, "Сделать отчет по лабе", "х2 за решение Тимуса", BigInteger.valueOf(200_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));
        allUpgrades.add(new Upgrade(65, R.mipmap.beer_factory, "ХХХ", "х2 за решение Тимуса", BigInteger.valueOf(4_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));
        allUpgrades.add(new Upgrade(66, R.mipmap.beer_factory, "ХХХ", "х2 за решение Тимуса", new BigInteger("32000000000000000000"))
                .addCondition(new BuildingCountCondition(ID_BEER, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));


        /*Final*/
        allUpgrades.add(new Upgrade(67, R.mipmap.dota, "Cделать лабу", "+4444444 за решение Тимуса", BigInteger.valueOf(3_500_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(999999), ID_DOTA)));
        allUpgrades.add(new Upgrade(68, R.mipmap.dota, "Узнать как работает лаба", "х2 за решение Тимуса", BigInteger.valueOf(17_500_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));
        allUpgrades.add(new Upgrade(69, R.mipmap.dota, "Ответить на вопросы", "х2 за решение Тимуса", BigInteger.valueOf(500_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));
        allUpgrades.add(new Upgrade(70, R.mipmap.dota, "Сделать отчет по лабе", "х2 за решение Тимуса", BigInteger.valueOf(21_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));
        allUpgrades.add(new Upgrade(71, R.mipmap.dota, "ХХХ", "х2 за решение Тимуса", BigInteger.valueOf(400_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));
        allUpgrades.add(new Upgrade(72, R.mipmap.dota, "ХХХ", "х2 за решение Тимуса", new BigInteger("2400000000000000000000"))
                .addCondition(new BuildingCountCondition(ID_DOTA, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));

        /*Dota*/
        allUpgrades.add(new Upgrade(73, R.mipmap.go_to_final, "Cделать лабу", "+1000000 за решение Тимуса", BigInteger.valueOf(750_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(999999), ID_FINAL)));
        allUpgrades.add(new Upgrade(74, R.mipmap.go_to_final, "Узнать как работает лаба", "х2 за решение Тимуса", BigInteger.valueOf(7_500_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));
        allUpgrades.add(new Upgrade(75, R.mipmap.go_to_final, "Ответить на вопросы", "х2 за решение Тимуса", BigInteger.valueOf(75_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));
        allUpgrades.add(new Upgrade(76, R.mipmap.go_to_final, "Сделать отчет по лабе", "х2 за решение Тимуса", BigInteger.valueOf(3_750_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));
        allUpgrades.add(new Upgrade(77, R.mipmap.go_to_final, "ХХХ", "х2 за решение Тимуса", BigInteger.valueOf(75_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));
        allUpgrades.add(new Upgrade(78, R.mipmap.go_to_final, "ХХХ", "х2 за решение Тимуса", new BigInteger("600000000000000000000"))
                .addCondition(new BuildingCountCondition(ID_FINAL, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));


        assertAllIdsUnique(allUpgrades);
    }

    private void assertAllIdsUnique(List<Upgrade> upgrades) {
        TreeSet<Integer> ids = new TreeSet<>();
        for (Upgrade upgrade : upgrades) ids.add(upgrade.getId());
        if (ids.size() != upgrades.size()) throw new RuntimeException("Some ids are not unique");
    }

    public List<Upgrade> getAllUpgrades() {
        return allUpgrades;
    }

    public List<Upgrade> getUnBoughtUpgrades() {
        List<Upgrade> unbought = new ArrayList<>();
        for (Upgrade upgrade : allUpgrades) {
            if (upgrade.isPreconditionsFullfilled() && !upgrade.isBought()) {
                unbought.add(upgrade);
            }
        }
        Collections.sort(unbought);
        return unbought;
    }
}
