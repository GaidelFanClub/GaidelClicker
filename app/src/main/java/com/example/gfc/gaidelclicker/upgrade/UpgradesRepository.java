package com.example.gfc.gaidelclicker.upgrade;

import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.upgrade.conditions.BuildingCountCondition;
import com.example.gfc.gaidelclicker.upgrade.conditions.GoldenCountCondition;
import com.example.gfc.gaidelclicker.upgrade.conditions.HandMadeGaidelsCondition;
import com.example.gfc.gaidelclicker.upgrade.conditions.UpgradesIsBoughtCondition;
import com.example.gfc.gaidelclicker.upgrade.conditions.WholeProfitCondition;
import com.example.gfc.gaidelclicker.upgrade.effects.ClickCpsPercentEffect;
import com.example.gfc.gaidelclicker.upgrade.effects.ClickFinalAddEffect;
import com.example.gfc.gaidelclicker.upgrade.effects.GoldenBoostEffect;
import com.example.gfc.gaidelclicker.upgrade.effects.SimpleAddEffect;
import com.example.gfc.gaidelclicker.upgrade.effects.SimpleMulEffect;
import com.example.gfc.gaidelclicker.upgrade.effects.WholePercentageEffect;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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

    private List<Upgrade> boughtUpgrades = new ArrayList<>();
    private List<Upgrade> availableUpgrades = new ArrayList<>();
    private List<Upgrade> unavailableUpgrades = new ArrayList<>();

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
        allUpgrades.add(new Upgrade(13, R.mipmap.twitch, "Зарегистрироваться на твиче", "+0.2 за просмотр твича", BigInteger.valueOf(500))
                .addCondition(new BuildingCountCondition(ID_TWITCH, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(0.2), ID_TWITCH)));
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
        allUpgrades.add(new Upgrade(19, R.mipmap.lecture, "Сесть на первую парту", "+0.5 за посещение лекции", BigInteger.valueOf(3000))
                .addCondition(new BuildingCountCondition(ID_LECTURE, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(0.5), ID_LECTURE)));
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

        allUpgrades.add(new Upgrade(25, R.mipmap.lab_work, "Cделать лабу", "+1 за сдачу лабы", BigInteger.valueOf(30_000))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(1), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(26, R.mipmap.lab_work, "Узнать как работает лаба", "х2 за сдачу лабы", BigInteger.valueOf(300_000))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(27, R.mipmap.lab_work, "Ответить на вопросы", "х2 за сдачу лабы", BigInteger.valueOf(3_000_000))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(28, R.mipmap.lab_work, "Сделать отчет по лабе", "х2 за сдачу лабы", BigInteger.valueOf(150_000_000L))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(29, R.mipmap.lab_work, "Заоптимайзить алгортм", "х2 за сдачу лабы", BigInteger.valueOf(3_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));
        allUpgrades.add(new Upgrade(30, R.mipmap.lab_work, "Сдать лабы до экзамена", "х2 за сдачу лабы", BigInteger.valueOf(24_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_LAB_WORK, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_LAB_WORK)));


        /*Practice*/
        allUpgrades.add(new Upgrade(31, R.mipmap.practice, "Послушать музыку пока делаешь ИДЗ", "+3 за сдачу идз", BigInteger.valueOf(100_000))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(3), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(32, R.mipmap.practice, "Отвлечься на мемосы", "х2 за сдачу идз", BigInteger.valueOf(1_000_000))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(33, R.mipmap.practice, "Посмотреть видосы на ютубе", "х2 за сдачу идз", BigInteger.valueOf(10_000_000))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(34, R.mipmap.practice, "Начать делать ИДЗ", "х2 за сдачу идз", BigInteger.valueOf(500_000_000L))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(35, R.mipmap.practice, "Делать ИДЗ всю ночь", "х2 за сдачу идз", BigInteger.valueOf(10_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));
        allUpgrades.add(new Upgrade(36, R.mipmap.practice, "Не уснуть перед парой", "х2 за сдачу идз", BigInteger.valueOf(80_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_PRACTICE, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_PRACTICE)));

        /*Cirlce*/
        allUpgrades.add(new Upgrade(37, R.mipmap.circle, "Послушать лекцию Гайделя", "+22.8 за посещение кружка", BigInteger.valueOf(400_000))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(22.8), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(38, R.mipmap.circle, "Вступить в группу но CodeForces", "х2 за посещение кружка", BigInteger.valueOf(4_000_000))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(39, R.drawable.upgrade_botan, "Решить 2 задачки", "х2 за посещение кружка", BigInteger.valueOf(40_000_000))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(40, R.mipmap.circle, "Послушать разбор", "х2 за посещение кружка", BigInteger.valueOf(2_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(41, R.mipmap.circle, "Остаться поиграть в мафию", "х2 за посещение кружка", BigInteger.valueOf(40_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));
        allUpgrades.add(new Upgrade(42, R.mipmap.circle, "Не убивать Гайделя в мафии", "х2 за посещение кружка", BigInteger.valueOf(320_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CIRCLE, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CIRCLE)));

        /*Cormen*/
        allUpgrades.add(new Upgrade(43, R.mipmap.cormen, "Прочитать предисловие", "+30 за чтение Кормена", BigInteger.valueOf(2_000_000))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(30), ID_CORMEN)));
        allUpgrades.add(new Upgrade(44, R.mipmap.cormen, "Прочитаь 1 главу", "х2 за чтение Кормена", BigInteger.valueOf(4_000_000))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));
        allUpgrades.add(new Upgrade(45, R.mipmap.cormen, "Прочитаь 2 главу", "х2 за чтение Кормена", BigInteger.valueOf(200_000_000))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));
        allUpgrades.add(new Upgrade(46, R.mipmap.cormen, "Прочитаь 3 главу", "х2 за чтение Кормена", BigInteger.valueOf(10_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));
        allUpgrades.add(new Upgrade(47, R.mipmap.cormen, "Прочитаь 4 главу", "х2 за чтение Кормена", BigInteger.valueOf(200_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));
        allUpgrades.add(new Upgrade(48, R.mipmap.cormen, "Прочитаь 5 главу", "х2 за чтение Кормена", BigInteger.valueOf(1_600_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CORMEN, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CORMEN)));

        /*Timus*/
        allUpgrades.add(new Upgrade(49, R.mipmap.task, "Зареистрировать аккаунт", "+167 за решение Тимуса", BigInteger.valueOf(15_000_000))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(167), ID_TIMUS)));
        allUpgrades.add(new Upgrade(50, R.mipmap.task, "Сдать А+Б", "х2 за решение Тимуса", BigInteger.valueOf(75_000_000))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));
        allUpgrades.add(new Upgrade(51, R.mipmap.task, "Посмотреть FAQ чтобы сдать задачу 1001", "х2 за решение Тимуса", BigInteger.valueOf(1_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));
        allUpgrades.add(new Upgrade(52, R.mipmap.task, "Решить все задачки для начинающих", "х2 за решение Тимуса", BigInteger.valueOf(25_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));
        allUpgrades.add(new Upgrade(53, R.mipmap.task, "Стырить решение с форума", "х2 за решение Тимуса", BigInteger.valueOf(1_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));
        allUpgrades.add(new Upgrade(54, R.mipmap.task, "Решить задач больше чем Гайдель", "х2 за решение Тимуса", BigInteger.valueOf(8_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_TIMUS, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_TIMUS)));

        /*CF*/
        allUpgrades.add(new Upgrade(55, R.mipmap.code_forces, "Решить А", "+1500 за участие в CF", BigInteger.valueOf(1_000_000_000))
                .addCondition(new BuildingCountCondition(ID_CF, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(1500), ID_CF)));
        allUpgrades.add(new Upgrade(56, R.mipmap.code_forces, "Решить Б", "х2 за участие в CF", BigInteger.valueOf(9_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));
        allUpgrades.add(new Upgrade(57, R.mipmap.code_forces, "Не слить рейтинг", "х2 за участие в CF", BigInteger.valueOf(99_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));
        allUpgrades.add(new Upgrade(58, R.mipmap.code_forces, "Перейти в первый дивизион", "х2 за участие в CF", BigInteger.valueOf(1_234_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));
        allUpgrades.add(new Upgrade(59, R.mipmap.code_forces, "Не вылететь из первого дивизиона", "х2 за участие в CF", BigInteger.valueOf(123_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));
        allUpgrades.add(new Upgrade(60, R.mipmap.code_forces, "Прокачать цвет", "х2 за участие в CF", BigInteger.valueOf(987_654_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_CF, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_CF)));

        /*BEER*/
        allUpgrades.add(new Upgrade(61, R.mipmap.beer_factory, "Разливать пиво в банки 0.5", "+5555.5 за пивзавод", BigInteger.valueOf(40_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(5555.5), ID_BEER)));
        allUpgrades.add(new Upgrade(62, R.mipmap.beer_factory, "Разливать пиво в бутылки 0.5", "х2 за пивзавод", BigInteger.valueOf(400_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));
        allUpgrades.add(new Upgrade(63, R.mipmap.beer_factory, "Разливать пиво в литровые бутылки", "х2 за пивзавод", BigInteger.valueOf(4_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));
        allUpgrades.add(new Upgrade(64, R.mipmap.beer_factory, "Разливать пиво в полторашки", "х2 за пивзавод", BigInteger.valueOf(200_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));
        allUpgrades.add(new Upgrade(65, R.mipmap.beer_factory, "Разливать пиво в пятилитровки", "х2 за пивзавод", BigInteger.valueOf(4_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_BEER, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));
        allUpgrades.add(new Upgrade(66, R.mipmap.beer_factory, "Разливать пиво в кеги", "х2 за пивзавод", new BigInteger("32000000000000000000"))
                .addCondition(new BuildingCountCondition(ID_BEER, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_BEER)));


        /*Dota*/
        allUpgrades.add(new Upgrade(67, R.mipmap.dota, "Пойти на мид", "+48999.999 за игру в доту", BigInteger.valueOf(3_500_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(48999.999), ID_DOTA)));
        allUpgrades.add(new Upgrade(68, R.mipmap.dota, "TODO", "х2 за игру в доту", BigInteger.valueOf(17_500_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));
        allUpgrades.add(new Upgrade(69, R.mipmap.dota, "TODO", "х2 за игру в доту", BigInteger.valueOf(500_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));
        allUpgrades.add(new Upgrade(70, R.mipmap.dota, "TODO", "х2 за игру в доту", BigInteger.valueOf(21_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));
        allUpgrades.add(new Upgrade(71, R.mipmap.dota, "TODO", "х2 за игру в доту", BigInteger.valueOf(400_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_DOTA, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));
        allUpgrades.add(new Upgrade(72, R.mipmap.dota, "TODO", "х2 за игру в доту", new BigInteger("2400000000000000000000"))
                .addCondition(new BuildingCountCondition(ID_DOTA, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_DOTA)));

        /*Final*/
        allUpgrades.add(new Upgrade(73, R.mipmap.go_to_final, "Взять кокоача", "+21345.67 за выход на финал", BigInteger.valueOf(750_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 1))
                .addEffect(new SimpleAddEffect(BigDecimal.valueOf(21345.67), ID_FINAL)));
        allUpgrades.add(new Upgrade(74, R.mipmap.go_to_final, "Заказать футболочки", "х2 за выход на финал", BigInteger.valueOf(7_500_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 1))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));
        allUpgrades.add(new Upgrade(75, R.mipmap.go_to_final, "Оформить визу", "х2 за выход на финал", BigInteger.valueOf(75_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 10))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));
        allUpgrades.add(new Upgrade(76, R.mipmap.go_to_final, "TODO", "х2 за выход на финал", BigInteger.valueOf(3_750_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 50))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));
        allUpgrades.add(new Upgrade(77, R.mipmap.go_to_final, "TODO", "х2 за выход на финал", BigInteger.valueOf(75_000_000_000_000_000L))
                .addCondition(new BuildingCountCondition(ID_FINAL, 100))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));
        allUpgrades.add(new Upgrade(78, R.mipmap.go_to_final, "TODO", "х2 за выход на финал", new BigInteger("600000000000000000000"))
                .addCondition(new BuildingCountCondition(ID_FINAL, 200))
                .addEffect(new SimpleMulEffect(BigDecimal.valueOf(2), ID_FINAL)));

        /*Whole percentage upgrades*/
        //10mln / 100mln / 5%
        allUpgrades.add(new Upgrade(79, R.drawable.upgrade_kind, "Добрый Гайдель", "+2% к получению баллов", new BigInteger("100000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000")))
                .addEffect(new WholePercentageEffect(2)));
        allUpgrades.add(new Upgrade(80, R.drawable.upgrade_sleep, "Сонный Гайдель", "+2% к получению баллов", new BigInteger("100000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000")))
                .addEffect(new WholePercentageEffect(2)));
        allUpgrades.add(new Upgrade(81, R.drawable.upgrade_simple, "Задумчивый Гайдель", "+2% к получению баллов", new BigInteger("100000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000")))
                .addEffect(new WholePercentageEffect(2)));
        allUpgrades.add(new Upgrade(82, R.drawable.upgrade_rotated, "Перевернутый Гайдель", "+2% к получению баллов", new BigInteger("100000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000")))
                .addEffect(new WholePercentageEffect(2)));

        //100mln / 1bln / 5%
        allUpgrades.add(new Upgrade(83, R.drawable.upgrade_displeased, "Непредсказуемый Гайдель", "+3% к получению баллов", new BigInteger("1000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("100000000")))
                .addEffect(new WholePercentageEffect(3)));
        allUpgrades.add(new Upgrade(84, R.drawable.upgrade_strange, "Странный Гайдель", "+3% к получению баллов", new BigInteger("1000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("100000000")))
                .addEffect(new WholePercentageEffect(3)));
        allUpgrades.add(new Upgrade(85, R.drawable.upgrade_displeased, "Плохой Гайдель", "+3% к получению баллов", new BigInteger("1000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("100000000")))
                .addEffect(new WholePercentageEffect(3)));

        //1bln / 100bln / 10%
        allUpgrades.add(new Upgrade(86, R.drawable.upgrade_simple, "Хороший Гайдель", "+5% к получению баллов", new BigInteger("100000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("1000000000")))
                .addEffect(new WholePercentageEffect(5)));
        allUpgrades.add(new Upgrade(87, R.drawable.upgrade_evil_2, "Злой Гайдель", "+6.66% к получению баллов", new BigInteger("100000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("1000000000")))
                .addEffect(new WholePercentageEffect(7)));//todo make it real 6.66
        allUpgrades.add(new Upgrade(88, R.drawable.upgrade_kind, "Няшный Гайдель", "+5% к получению баллов", new BigInteger("100000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("1000000000")))
                .addEffect(new WholePercentageEffect(5)));

        //10bln / 1tln / 15%
        allUpgrades.add(new Upgrade(89, R.drawable.upgrade_displeased, "Опасный Гайдель", "+7% к получению баллов", new BigInteger("1000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000000")))
                .addEffect(new WholePercentageEffect(7)));
        allUpgrades.add(new Upgrade(90, R.drawable.upgrade_displeased, "Уличный Гайдель", "+7% к получению баллов", new BigInteger("1000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000000")))
                .addEffect(new WholePercentageEffect(7)));

        //100bln / 10tln / 15%
        allUpgrades.add(new Upgrade(91, R.drawable.upgrade_home, "Домашний Гайдель", "+10% к получению баллов", new BigInteger("10000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000000")))
                .addEffect(new WholePercentageEffect(10)));
        allUpgrades.add(new Upgrade(92, R.drawable.upgrade_troll, "Шутливый Гайдель", "+10% к получению баллов", new BigInteger("10000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000000")))
                .addEffect(new WholePercentageEffect(10)));

        //1tln / 100tln / 15%
        allUpgrades.add(new Upgrade(93, R.drawable.upgrade_sport, "Молодой Гайдель", "+11% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("100000000000")))
                .addEffect(new WholePercentageEffect(11)));
        allUpgrades.add(new Upgrade(94, R.drawable.upgrade_old, "Старый Гайдель", "+11% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("100000000000")))
                .addEffect(new WholePercentageEffect(11)));
        allUpgrades.add(new Upgrade(95, R.drawable.upgrade_troll, "Заводной Гайдель", "+11% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("100000000000")))
                .addEffect(new WholePercentageEffect(11)));

        allUpgrades.add(new Upgrade(96, R.drawable.upgrade_strange, "Профессиональный Гайдель", "+12% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new UpgradesIsBoughtCondition(allUpgrades, 93, 94, 95))
                .addEffect(new WholePercentageEffect(12)));
        allUpgrades.add(new Upgrade(97, R.drawable.upgrade_simple, "Загадочный Гайдель", "+13% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new UpgradesIsBoughtCondition(allUpgrades, 96))
                .addEffect(new WholePercentageEffect(13)));
        allUpgrades.add(new Upgrade(98, R.drawable.upgrade_sleep, "Талантливый Гайдель", "+14% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new UpgradesIsBoughtCondition(allUpgrades, 97))
                .addEffect(new WholePercentageEffect(14)));
        allUpgrades.add(new Upgrade(99, R.drawable.upgrade_sport, "Спортивный Гайдель", "+15% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new UpgradesIsBoughtCondition(allUpgrades, 98))
                .addEffect(new WholePercentageEffect(15)));
        allUpgrades.add(new Upgrade(100, R.drawable.upgrade_strange, "Мечтающий Гайдель", "+16% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new UpgradesIsBoughtCondition(allUpgrades, 99))
                .addEffect(new WholePercentageEffect(16)));
        allUpgrades.add(new Upgrade(101, R.drawable.upgrade_troll, "Веселый Гайдель", "+17% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new UpgradesIsBoughtCondition(allUpgrades, 100))
                .addEffect(new WholePercentageEffect(17)));
        allUpgrades.add(new Upgrade(102, R.drawable.upgrade_sad, "Грустный Гайдель", "+19% к получению баллов", new BigInteger("100000000000000"))
                .addCondition(new UpgradesIsBoughtCondition(allUpgrades, 101))
                .addEffect(new WholePercentageEffect(19)));

        allUpgrades.add(new Upgrade(103, R.drawable.upgrade_simple, "Обычный Гайдель", "+20% к получению баллов", new BigInteger("200000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000000000")))
                .addEffect(new WholePercentageEffect(20)));
        allUpgrades.add(new Upgrade(104, R.drawable.upgrade_troll, "Радостный Гайдель", "+20% к получению баллов", new BigInteger("200000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000000000")))
                .addEffect(new WholePercentageEffect(20)));
        allUpgrades.add(new Upgrade(105, R.drawable.upgrade_troll, "Хихикающий Гайдель", "+20% к получению баллов", new BigInteger("200000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000000000")))
                .addEffect(new WholePercentageEffect(20)));
        allUpgrades.add(new Upgrade(106, R.drawable.upgrade_kind, "Щедрый Гайдель", "+20% к получению баллов", new BigInteger("200000000000000"))
                .addCondition(new WholeProfitCondition(new BigDecimal("10000000000000")))
                .addEffect(new WholePercentageEffect(20)));

        allUpgrades.add(new Upgrade(107, R.drawable.tap_red, "Пластиковый палец", "+1% от значения гайдели/сек за клик", new BigInteger("50000"))
                .addCondition(new HandMadeGaidelsCondition(new BigDecimal("1000")))
                .addEffect(new ClickCpsPercentEffect()));
        allUpgrades.add(new Upgrade(108, R.drawable.tap_orange, "Железный палец", "+1% от значения гайдели/сек за клик", new BigInteger("5000000"))
                .addCondition(new HandMadeGaidelsCondition(new BigDecimal("100000")))
                .addEffect(new ClickCpsPercentEffect()));
        allUpgrades.add(new Upgrade(109, R.drawable.tap_yellow, "Титановый палец", "+1% от значения гайдели/сек за клик", new BigInteger("500000000"))
                .addCondition(new HandMadeGaidelsCondition(new BigDecimal("10000000")))
                .addEffect(new ClickCpsPercentEffect()));
        allUpgrades.add(new Upgrade(110, R.drawable.tap_green, "Алмазный палец", "+1% от значения гайдели/сек за клик", new BigInteger("50000000000"))
                .addCondition(new HandMadeGaidelsCondition(new BigDecimal("1000000000")))
                .addEffect(new ClickCpsPercentEffect()));
        allUpgrades.add(new Upgrade(111, R.drawable.tap_blue, "Адамантиевый палец", "+1% от значения гайдели/сек за клик", new BigInteger("5000000000000"))
                .addCondition(new HandMadeGaidelsCondition(new BigDecimal("100000000000")))
                .addEffect(new ClickCpsPercentEffect()));
        allUpgrades.add(new Upgrade(112, R.drawable.tap_dark_blue, "Унобтаниевый палец", "+1% от значения гайдели/сек за клик", new BigInteger("500000000000000"))
                .addCondition(new HandMadeGaidelsCondition(new BigDecimal("10000000000000")))
                .addEffect(new ClickCpsPercentEffect()));
        allUpgrades.add(new Upgrade(113, R.drawable.tap_violet, "Элюдиевый палец", "+1% от значения гайдели/сек за клик", new BigInteger("50000000000000000"))
                .addCondition(new HandMadeGaidelsCondition(new BigDecimal("1000000000000000")))
                .addEffect(new ClickCpsPercentEffect()));

        allUpgrades.add(new Upgrade(114, R.mipmap.gold_gaidel, "Удачный день", "Золотой Гайдель выпадает вдвое чаще и не исчезает вдвое дольше", new BigInteger("777777777"))
                .addCondition(new GoldenCountCondition(7))
                .addEffect(new GoldenBoostEffect(2, 2, 1)));
        allUpgrades.add(new Upgrade(115, R.mipmap.gold_gaidel, "Счастливый билет", "Золотой Гайдель выпадает вдвое чаще и не исчезает вдвое дольше", new BigInteger("77777777777"))
                .addCondition(new GoldenCountCondition(27))
                .addEffect(new GoldenBoostEffect(2, 2, 1)));
        allUpgrades.add(new Upgrade(116, R.mipmap.gold_gaidel, "Пацанский фарт", "Эффект золотого Гайделя длится вдвое дольше", new BigInteger("77777777777777"))
                .addCondition(new GoldenCountCondition(77))
                .addEffect(new GoldenBoostEffect(1, 1, 2)));


        assertAllIdsUnique(allUpgrades);
        unavailableUpgrades.addAll(allUpgrades);
        checkUpgradeStates();
    }

    private void assertAllIdsUnique(List<Upgrade> upgrades) {
        TreeSet<Integer> ids = new TreeSet<>();
        for (Upgrade upgrade : upgrades) ids.add(upgrade.getId());
        if (ids.size() != upgrades.size()) throw new RuntimeException("Some ids are not unique");
    }

    public List<Upgrade> getAllUpgrades() {
        return allUpgrades;
    }

    public List<Upgrade> getBoughtUpgrades() {
        return boughtUpgrades;
    }

    public List<Upgrade> getAvailableUpgrades() {
        checkUpgradeStates();
        return availableUpgrades;
    }

    public int getBoughtCount() {
        return boughtUpgrades.size();
    }

    public int getAllUpgradesCount() {
        return allUpgrades.size();
    }

    public void refresh() {
        checkUpgradeStates();
    }

    private void checkUpgradeStates() {
        boolean availableUpgradesChanged = false;
        for (int i = 0; i < unavailableUpgrades.size(); i++) {
            Upgrade upgrade = unavailableUpgrades.get(i);
            if (upgrade.isPreconditionsFullfilled()) {
                availableUpgrades.add(upgrade);
                unavailableUpgrades.remove(i--);
                availableUpgradesChanged = true;
            }
        }
        if (availableUpgradesChanged) {
            Collections.sort(availableUpgrades);
        }
        for (int i = 0; i < availableUpgrades.size(); i++) {
            Upgrade upgrade = availableUpgrades.get(i);
            if (upgrade.isBought()) {
                boughtUpgrades.add(upgrade);
                availableUpgrades.remove(i--);
            }
        }
    }

}
