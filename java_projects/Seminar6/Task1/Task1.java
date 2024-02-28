package Task1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*Задание №1
1. Напишите метод, который заполнит массив из 1000 элементов случайными
цифрами от 0 до 500
2. Создайте метод, в который передайте заполненный выше массив и с
помощью Set вычислите процент уникальных значений в данном массиве и
верните его в виде числа с плавающей запятой.
Для вычисления процента используйте формулу:
процент уникальных чисел = количество уникальных чисел * 100 / общее
количество чисел в массиве. */
public class Task1 {
    public static void main(String[] args) {
        System.out.println(getPercentUniqElement(getArray(1000, 500)));
    }

    public static int[] getArray(int size, int max) {
        int [] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max);
        }
        return array;
    }

    public static double getPercentUniqElement(int [] array) {
        Set<Integer> integerSet = new HashSet<>();
        for (int i: array) {
            integerSet.add(i);
        }
        double uniq = integerSet.size() * 100/ (double)array.length;
        return uniq;
    }
}