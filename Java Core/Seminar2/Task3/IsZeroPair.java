package Task3;

/**
 * Написать функцию, возвращающую истину, если в переданном массиве есть два
 * соседних элемента с нулевым значением.
 */

public class IsZeroPair {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 10, 11, 12, 0, 0, 35, -45, -46, -57 };
        if(isZeroPairsZeroPair(array) == true) {
            System.out.println("В массиве есть два соседних нулевых значения.");
        } else {
            System.out.println("В массиве нет двух соседних нулевых значений.");
        }
    }

    public static boolean isZeroPairsZeroPair(int[] array) {
        boolean result = false;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == 0 && array[i + 1] == 0)
                result = true;
        }
        return result;
    }
}
