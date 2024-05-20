import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void raiseSalaries(List<Employee> employees, double amount) {
        for (Employee e : employees) {
            if (!(e instanceof Manager)) {
                e.setSalary(e.getSalary() + amount);
            }
        }
    }

    public static int compareDates(Employee e1, Employee e2) {
        int[] date1 = {e1.getYear(), e1.getMonth(), e1.getDay()};
        int[] date2 = {e2.getYear(), e2.getMonth(), e2.getDay()};

        for (int i = 0; i < 3; i++) {
            int comparison = Integer.compare(date1[i], date2[i]);
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Employee emp1 = new Employee("Алиса", 2021, 5, 20, 50000);
        Employee emp2 = new Employee("Борис", 2024, 1, 12, 60000);
        Manager manager = new Manager("Валерий", 2020, 11, 4, 80000);

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(manager);

        System.out.println("До повышения:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        raiseSalaries(employees, 5000);

        System.out.println("\nПосле повышения:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("\nСравнение дат:");
        System.out.println("Алиса vs Борис: " + compareDates(emp1, emp2));
        System.out.println("Алиса vs Валерий: " + compareDates(emp1, manager));
    }
}
