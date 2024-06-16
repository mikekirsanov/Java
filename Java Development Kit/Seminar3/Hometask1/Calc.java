/**
 * Написать класс Калькулятор (необобщенный), который содержит обобщенные
 * статические методы: sum(), multiply(), divide(), subtruct(). Параметры этих
 * методов - два числа разного типа, над которыми должна быть произведена
 * операция.
 */
public class Calc {

    public static <T extends Number, U extends Number> double sum(T a, U b) {
        return a.doubleValue() + b.doubleValue();
    }

    public static <T extends Number, U extends Number> double multiply(T a, U b) {
        return a.doubleValue() * b.doubleValue();
    }

    public static <T extends Number, U extends Number> double divide(T a, U b) {
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("Деление на ноль запрещено");
        }
        return a.doubleValue() / b.doubleValue();
    }

    public static <T extends Number, U extends Number> double subtract(T a, U b) {
        return a.doubleValue() - b.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(Calc.sum(1, 2.5));
        System.out.println(Calc.multiply(1, 2.5));
        System.out.println(Calc.divide(1, 2.5));
        System.out.println(Calc.subtract(1, 2.5));
        System.out.println(Calc.subtract(1, 2.5));
    }
}