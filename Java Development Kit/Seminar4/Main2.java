import java.util.*;

/**
 * В рамках выполнения задачи необходимо:
 * Создайте коллекцию мужских и женских имен с помощью интерфейса List - добавьте повторяющиеся значения
 * Получите уникальный список Set на основании List
 * Определите наименьший элемент (алфавитный порядок)
 * Определите наибольший элемент (по количеству букв в слове но в обратном порядке)
 * Удалите все элементы содержащие букву ‘A’
 */
public class Main2 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Валерий");
        names.add("Зиновий");
        names.add("Ариадна");
        names.add("Мефистофель");
        names.add("Евгений");
        names.add("Гера");
        names.add("Валерий");
        names.add("Евгения");
        names.add("Зинаида");

        Set<String> uniqueNames = new HashSet<>(names);
        System.out.println(uniqueNames);
        String minName = uniqueNames.stream().min(String::compareTo).orElse(null);
        System.out.println(minName);
        String maxName = uniqueNames.stream().max(Comparator.comparingInt(String::length)).orElse(null);
        System.out.println(maxName);
        uniqueNames.removeIf(name -> name.contains("А") || name.contains("а"));
        System.out.println("Имена без буквы 'А': " + uniqueNames);
    }
}
