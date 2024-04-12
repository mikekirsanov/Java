import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
/*Напишите приложение, которое будет запрашивать у пользователя следующие данные, разделенные пробелом:

Фамилия Имя Отчество дата _ рождения номер _ телефона пол

Форматы данных:

фамилия, имя, отчество - строки
дата _ рождения - строка формата dd.mm.yyyy
номер _ телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно распарсить полученную строку и выделить из них требуемые значения. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
<Фамилия> <Имя> <Отчество> <дата _ рождения> <номер _ телефона> <пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом.
При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки. */
public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Введите Фамилию Имя Отчество Дату_рождения Номер_телефона Пол через пробел:");
            String input = reader.readLine();

            String[] parts = input.trim().split("\\s+");
            for (String part : parts) {
                System.out.println(part);
            }
            // Проверка корректности количества введенных данных
            if (parts.length != 6) {
                System.out.println("Ошибка: неверное количество данных");
                return;
            }

            // Сохранение каждой части в соответствующую переменную
            String lastName = parts[0];
            String firstName = parts[1];
            String middleName = parts[2];
            String birthDate = parts[3];
            long phoneNumber;
            char gender;

            try {
                phoneNumber = Long.parseLong(parts[4]);
                gender = parts[5].charAt(0);
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("Ошибка: некорректные данные для номера телефона или пола");
                return;
            }

            // Проверка данных на корректность
            try {
                validateData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
                System.out.println("Данные верные для: " + lastName + " " + firstName + " " + middleName);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + " для: " + lastName + " " + firstName + " " + middleName);
                return;
            }

            Person person = new Person(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            // Сохранение данных в файл
            try {
                saveToFile(person);
                System.out.println("Данные успешно сохранены в файл.");
            } catch (IOException e) {
                System.out.println("Ошибка при сохранении данных в файл: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Person {
        private String lastName;
        private String firstName;
        private String middleName;
        private String birthDate;
        private long phoneNumber;
        private char gender;

        public Person(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
        }
    }

    static void validateData(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) throws IllegalArgumentException {
        // Проверка даты рождения
        if (!birthDate.matches("\\d{8}")) {
            throw new IllegalArgumentException("Некорректная дата рождения");
        }

        // Проверка номера телефона
        if (phoneNumber < 0 || !String.valueOf(phoneNumber).matches("\\d+")) {
            throw new IllegalArgumentException("Некорректный номер телефона");
        }

        // Проверка пола
        if (gender != 'f' && gender != 'm') {
            throw new IllegalArgumentException("Некорректный пол");
        }
    }

    static void saveToFile(Person person) throws IOException {
        // Создание файла с именем, равным фамилии
        String fileName = person.lastName + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(person.lastName + " " + person.firstName + " " + person.middleName + " " + person.birthDate + " " + person.phoneNumber + " " + person.gender);
        }
    }
}
