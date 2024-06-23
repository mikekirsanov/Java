package Homework;

/**
 * Создать справочник сотрудников
 * Необходимо:
 * Создать класс справочник сотрудников, который содержит внутри
 * коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
 * Табельный номер
 * Номер телефона
 * Имя
 * Стаж
 * Добавить метод, который ищет сотрудника по стажу (может быть список)
 * Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
 * Добавить метод, который ищет сотрудника по табельному номеру
 * Добавить метод добавления нового сотрудника в справочник
 */
public class Main {
    public static void main(String[] args) {
        Handbook handbook = new Handbook();

        handbook.addEmployee(new Employee("+79221234567", "Валерий Иванов", 5));
        handbook.addEmployee(new Employee("79234675678", "Михаил Петров", 3));
        handbook.addEmployee(new Employee("79257542334", "Оксана Сидорова", 2));
        handbook.addEmployee(new Employee("792575251234", "Кристина Агатова", 6));
        handbook.addEmployee(new Employee("79277590874", "Геннадий Фролов", 3));

        System.out.println("Сотрудники со стажем 3 года:");
        for (Employee e : handbook.findEmployeesBySeniority(3)) {
            System.out.println(e);
        }

        System.out.print("Номер телефона Валерия Иванова: ");
        for (String number : handbook.getPhoneNumbersByName("Валерий Иванов")) {
            System.out.println(number);
        }

        System.out.println("Сотрудник с табельным номером 2:");
        System.out.println(handbook.findEmployeeByNumber(2));
    }
}
