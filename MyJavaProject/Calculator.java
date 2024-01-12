import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Простой калькулятор на Java");
        System.out.print("Введите первое число: ");
        double num1 = scanner.nextDouble();

        System.out.print("Введите операцию (+, -, *, /): ");
        char operation = scanner.next().charAt(0);

        System.out.print("Введите второе число: ");
        double num2 = scanner.nextDouble();

        double result = calculate(num1, operation, num2);
        System.out.println("Результат: " + result);
        scanner.close();
    }

    public static double calculate(double num1, char operation, double num2) {
        double result = 0;

        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Ошибка: деление на ноль.");
                    System.exit(1); // Завершить программу с кодом ошибки 1
                }
                break;
            default:
                System.out.println("Ошибка: неверная операция.");
                System.exit(1);
        }

        return result;
    }
}
