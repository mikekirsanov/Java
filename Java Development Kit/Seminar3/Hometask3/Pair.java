/**
 * Напишите обобщенный класс Pair, который представляет собой пару значений
 * разного типа. Класс должен иметь методы getFirst(), getSecond() для получения
 * значений пары, а также переопределение метода toString(), возвращающее
 * строковое представление пары.
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Pair {" + "first = " + first + ", second = " + second + "}";
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>(1, "one");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);
    }
}
