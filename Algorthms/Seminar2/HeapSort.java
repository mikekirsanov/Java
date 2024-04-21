package Seminar2;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    public static void main(String[] args) {
        // Создаем случайный массив
        int[] array = generateRandomArray(20);

        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(array));

        heapSort(array);

        System.out.println("Отсортированный массив:");
        System.out.println(Arrays.toString(array));
    }

    // Генератор случайного массива
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(-99,100); // Генерация случайного числа от -99 до 99
        }
        return array;
    }

    // Пирамидальная сортировка
    public static void heapSort(int[] array) {
        int size = array.length;

        // Построение кучи
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i);
        }

        // Извлекаем элементы из кучи
        for (int i = size - 1; i > 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем heapify на уменьшенной куче
            heapify(array, i, 0);
        }
    }

    // Построение кучи
    public static void heapify(int[] array, int n, int i) {
        int largest = i; // Инициализация наибольшего элемента как корня
        int leftChild = 2 * i + 1; // Левый потомок
        int rightChild = 2 * i + 2; // Правый потомок

        // Если левый потомок больше корня
        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        // Если правый потомок больше, чем самый большой элемент
        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // Если самый большой элемент не корень
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Рекурсивно строим кучу для поддерева
            heapify(array, n, largest);
        }
    }
}
