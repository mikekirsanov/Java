package Task2;

/**
 * Написать функцию, возвращающую разницу между самым большим и самым маленьким
 * элементами переданного не пустого массива.
 */

public class ArrayRange {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 10, 11, 12, 0, 0, 35, -45, -46, -57 };
        System.out.println("Разница между максимальным и минимальным элементами массива равна: " + range(array));

    }

    public static int range(int[] array) {
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i])
                max = array[i];
            if (min > array[i])
                min = array[i];
        }
        return max - min;
    }
}
