package Task1;

/**
 * Написать метод, возвращающий количество чётных элементов массива.
 * countEvens([2, 1, 2, 3, 4]) → 3 countEvens([2, 2, 0]) → 3 countEvens([1, 3,
 * 5]) → 0
 */
public class EvenElements {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 10, 11, 12, 0, 0, 35, -45, -46, -57 };
        System.out.println("Количество четных элементов массива равно: " + countEvens(array));
    }

    public static int countEvens(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result += 1;
            }
        }
        return result;
    }
}
