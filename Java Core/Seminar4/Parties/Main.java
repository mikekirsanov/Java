package Parties;

import Parties.Employee.Genders;

public class Main {
    public static void main(String[] args) {
        final Employee[] employees = {
                new Employee("Alexandr", "Alexandrovich", "Alexandrov", "+1-111-222-33-44", "Senior developer", 70000, 40, Genders.MALE),
                new Employee("Boris", "Borisovich", "Borisov", "+1-111-222-33-45", "Junior developer", 40000, 20, Genders.MALE),
                new Employee("Valeriya", "Valerievna", "Valter", "+1-111-222-33-46", "Product manager", 80000, 35, Genders.FEMALE),
                new Employee("Galina", "Gennadievna", "Gargopina", "+1-111-222-33-47", "Cleaner", 30000, 50, Genders.FEMALE),
                new Employee("Dmitry", "Dmitrievich", "Dmitriev", "+1-111-222-33-33", "CEO", 700000, 45, Genders.MALE),
        };

        // Текущий день праздника
        Parties today = Parties.MARCH_8;

        celebrate(employees, today);
    }

    enum Parties {
        NONE, NEW_YEAR, MARCH_8, FEB_23
    }

    private static void celebrate(Employee[] emp, Parties today) {
        for (Employee employee : emp) {
            switch (today) {
                case NEW_YEAR:
                    System.out.println(employee.name + ", happy New Year!");
                    break;
                case FEB_23:
                    if (employee.gender == Genders.MALE)
                        System.out.println(employee.name + ", happy February 23rd!");
                    break;
                case MARCH_8:
                    if (employee.gender == Genders.FEMALE)
                        System.out.println(employee.name + ", happy March 8th!");
                    break;
                default:
                    System.out.println(employee.name + ", celebrate this morning!");
            }
        }
    }
}
