import java.util.*;
import java.util.stream.Collectors;

public class Homework {

    private static class Department {
        private String name;

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static class Person {
        private String name;
        private int age;
        private double salary;
        private Department depart;

        public Person(String name, int age, double salary, Department depart) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.depart = depart;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getSalary() {
            return salary;
        }

        public Department getDepart() {
            return depart;
        }

    }

    /**
     * Найти самого молодого сотрудника
     */
    static Optional<Person> findMostYoungestPerson(List<Person> people) {
        return people.stream().min(Comparator.comparingInt(Person::getAge));
    }

    /**
     * Найти департамент, в котором работает сотрудник с самой большой зарплатой
     */
    static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
        return people.stream()
                .max(Comparator.comparingDouble(Person::getSalary))
                .map(Person::getDepart);
    }

    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Optional<Map<Department, List<Person>>> groupByDepartment(List<Person> people) {
        if (people.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(people.stream().collect(Collectors.groupingBy(Person::getDepart)));
    }

    /**
     * Сгруппировать сотрудников по названиям департаментов
     */
    static Optional<Map<String, List<Person>>> groupByDepartmentName(List<Person> people) {
        if (people.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(people.stream().collect(Collectors.groupingBy(p -> p.getDepart().getName())));
    }

    /**
     * В каждом департаменте найти самого старшего сотрудника
     */
    static Optional<Map<String, Person>> getDepartmentOldestPerson(List<Person> people) {
        if (people.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(people.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getDepart().getName(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Person::getAge)),
                                Optional::get
                        )
                )));
    }

    /**
     * Найти сотрудников с минимальными зарплатами в своем отделе
     * (прим. можно реализовать в два запроса)
     */
    static Optional<List<Person>> cheapPersonsInDepartment(List<Person> people) {
        if (people.isEmpty()) {
            return Optional.empty();
        }
        Map<String, Optional<Person>> minSalariesByDept = people.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getDepart().getName(),
                        Collectors.minBy(Comparator.comparingDouble(Person::getSalary))
                ));

        List<Person> result = minSalariesByDept.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return Optional.of(result);
    }

    public static void main(String[] args) {
        Department dept1 = new Department("HR");
        Department dept2 = new Department("IT");
        Department dept3 = new Department("Finance");

        Person emp1 = new Person("Виктория Кораблева", 30, 50000, dept1);
        Person emp2 = new Person("Денис Выходцев", 25, 55000, dept1);
        Person emp3 = new Person("Александр Климов", 28, 45000, dept1);

        Person emp4 = new Person("Дмитрий Олейников", 35, 60000, dept2);
        Person emp5 = new Person("Оксана Петрова", 32, 62000, dept2);
        Person emp6 = new Person("Игорь Фролов", 29, 58000, dept2);

        Person emp7 = new Person("Галина Евстигнеева", 40, 70000, dept3);
        Person emp8 = new Person("Валентина Харитонова", 45, 68000, dept3);
        Person emp9 = new Person("Ильдар Бултачеев", 38, 71000, dept3);

        List<Person> people = Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9);

        findMostYoungestPerson(people).ifPresent(person ->
                System.out.println("Самый молодой сотрудник: " + person.getName() + ", возраст: " + person.getAge())
        );

        findMostExpensiveDepartment(people).ifPresent(department ->
                System.out.println("Департамент с самой большой зарплатой сотрудника: " + department.getName())
        );

        System.out.println("Сотрудники по департаментам:");
        groupByDepartment(people).ifPresent(map -> map.forEach((department, persons) -> {
            System.out.println(department.getName() + ":");
            persons.forEach(person -> System.out.println("  " + person.getName()));
        }));

        System.out.println("Сотрудники по названиям департаментов:");
        groupByDepartmentName(people).ifPresent(map -> map.forEach((deptName, persons) -> {
            System.out.println(deptName + ":");
            persons.forEach(person -> System.out.println("  " + person.getName()));
        }));

        System.out.println("Самый старший сотрудник в каждом департаменте:");
        getDepartmentOldestPerson(people).ifPresent(map -> map.forEach((deptName, person) -> System.out.println(deptName + ": " + person.getName() + ", возраст: " + person.getAge())));

        System.out.println("Сотрудники с минимальными зарплатами в своих отделах:");
        cheapPersonsInDepartment(people).ifPresent(list -> list.forEach(person -> System.out.println(person.getDepart().getName() + ": " + person.getName() + ", зарплата: " + person.getSalary())));
    }
}
