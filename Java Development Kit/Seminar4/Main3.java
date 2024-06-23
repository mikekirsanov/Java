import java.util.HashMap;
import java.util.Map;

/**
 * В рамках выполнения задачи необходимо:
 * 1. Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение
 * 2. Найдите человека с самым маленьким номером телефона
 * 3. Найдите номер телефона человека чье имя самое большое в алфавитном порядке
 */

public class Main3 {
    public static void main(String[] args) {
        // Создание телефонного справочника
        HashMap<String, String> phonebook = new HashMap<>();
        phonebook.put("87772455667", "Petr Ivanov");
        phonebook.put("87772455662", "Ivan Petrov");
        phonebook.put("87772455645", "Dmitry Mendeleev");
        phonebook.put("87772455123", "Valery Kuzmin");
        phonebook.put("87772671667", "Valerya Chusova");
        phonebook.put("87778978907", "Kira Borisova");

        // Нахождение человека с самым маленьким номером телефона
        String minPhone = phonebook.keySet().stream().min(String::compareTo).orElse(null);
        if (minPhone != null) {
            System.out.println("Человек с самым маленьким номером телефона: " + phonebook.get(minPhone) + " с номером " + minPhone);
        } else {
            System.out.println("Телефонный справочник пуст.");
        }

        // Нахождение номера телефона человека, чье имя самое большое в алфавитном порядке
        String maxName = phonebook.values().stream().max(String::compareTo).orElse(null);
        String phoneOfMaxName = null;
        for (Map.Entry<String, String> entry : phonebook.entrySet()) {
            if (entry.getValue().equals(maxName)) {
                phoneOfMaxName = entry.getKey();
                break;
            }
        }

        if (maxName != null) {
            System.out.println("Номер телефона человека, чье имя самое большое в алфавитном порядке: " + maxName + " с номером " + phoneOfMaxName);
        } else {
            System.out.println("Телефонный справочник пуст.");
        }
    }
}

