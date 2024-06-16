/**
 * Напишите обобщенный метод compareArrays(), который принимает два массива и
 * возвращает true, если они одинаковые и false в противном случае. Массивы
 * могут быть любого типа данных, но должны иметь одинаковую длину и содержать
 * элементы одного типа попарно (под одинаковыми индексами элементы одного
 * типа).
 *
 */

public class CompareArrays {
    public static <T> boolean compareArrays(T[] array1, T[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].getClass().equals(array2[i].getClass())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] array1 = { 1, 2, 3, 4, 5 };
        Number[] array2 = { 6, 6, 6, 6, 6 };
        String[] array3 = { "6", "7", "8", "9", "0" };
        Integer[] array4 = { 6, 7, 8, 9, 0 };
        Object[] array5 = { "1", "2", "3", "4", "5" };

        System.out.println(compareArrays(array1, array2));
        System.out.println(compareArrays(array1, array3));
        System.out.println(compareArrays(array3, array4));
        System.out.println(compareArrays(array3, array5));
    }
}
