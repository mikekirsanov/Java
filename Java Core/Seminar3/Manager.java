import java.util.List;

public class Manager extends Employee {
    public Manager(String name, int year, int month, int day, double salary) {
        super(name, year, month, day, salary);
    }

    public static void raiseSalaries(List<Employee> employees, double amount) {
        for (Employee e : employees) {
            if (!(e instanceof Manager)) {
                e.setSalary(e.getSalary() + amount);
            }
        }
    }

    @Override
    public String toString() {
        return "Руководитель" + super.toString().substring(8);
    }
}
