package com.example.montyhall;
/**
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Подключить зависимость lombok.
 * Можно подумать о подключении какой-нибудь математической библиотеки для работы со статистикой
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>
 * Вывести на экран статистику по победам и поражениям
 *
 * Работы принимаются в виде ссылки на гит репозиторий со всеми ключевыми файлами проекта
 */

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class MontyHallSimulation {
    private static final int NUM_TRIALS = 1000;

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Random rand = new Random();
        DescriptiveStatistics stayStats = new DescriptiveStatistics();
        DescriptiveStatistics switchStats = new DescriptiveStatistics();
        Map<Integer, Boolean> stayResults = new HashMap<>();
        Map<Integer, Boolean> switchResults = new HashMap<>();

        for (int i = 0; i < NUM_TRIALS; i++) {
            boolean stayWin = simulateMontyHall(rand, false);
            boolean switchWin = simulateMontyHall(rand, true);
            stayStats.addValue(stayWin ? 1 : 0);
            switchStats.addValue(switchWin ? 1 : 0);
            stayResults.put(i + 1, stayWin);
            switchResults.put(i + 1, switchWin);
        }

        System.out.println("Сводная статистика:");
        System.out.printf("Стратегия остаться - Среднее количество побед: %.3f | Стандартное отклонение: %.12f%n", stayStats.getMean(), stayStats.getStandardDeviation());
        System.out.printf("Стратегия переключиться - Среднее количество побед: %.3f | Стандартное отклонение: %.12f%n", switchStats.getMean(), switchStats.getStandardDeviation());
    }

    private static boolean simulateMontyHall(Random rand, boolean switchStrategy) {
        int car = rand.nextInt(3);
        int choice = rand.nextInt(3);

        int shown;
        do {
            shown = rand.nextInt(3);
        } while (shown == car || shown == choice);

        if (switchStrategy) {
            choice = 3 - choice - shown;
        }

        return choice == car;
    }
}
