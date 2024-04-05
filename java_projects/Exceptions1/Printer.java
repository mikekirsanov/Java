class Answer {
    public static void arrayOutOfBoundsException() {
        // Напишите свое решение ниже
            int[] array = { 1, 2, 3, 4 };
            int a = array[5];
    }

    public static void divisionByZero() {
        // Напишите свое решение ниже
            int b = 5 / 0;
    }

    public static void numberFormatException() {
        // Напишите свое решение ниже
            String abc = "abc";
            int num = Integer.parseInt(abc);
    }
}

public class Printer {
    public static void main(String[] args) {
        Answer ans = new Answer();
        try {
            ans.arrayOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива");
        }

        try {
            ans.divisionByZero();
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль");
        }

        try {
            ans.numberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования строки в число");
        }
    }
}