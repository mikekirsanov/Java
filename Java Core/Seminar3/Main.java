import java.util.List;
import java.util.ArrayList;

/**
 * Написать прототип компаратора - метод внутри класса сотрудника, сравнивающий
 * две даты, представленные в виде трёх чисел гггг-мм-дд, без использования
 * условного оператора.
 * Опишите класс руководителя, наследник от сотрудника. Перенесите статический
 * метод повышения зарплаты в класс руководителя, модифицируйте метод таким
 * образом, чтобы он мог поднять заработную плату всем, кроме руководителей. В
 * основной программе создайте руководителя и поместите его в общий массив
 * сотрудников. Повысьте зарплату всем сотрудникам и проследите, чтобы зарплата
 * руководителя не повысилась.
 */
public class Main {

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

        Manager.raiseSalaries(employees, 5000);

        System.out.println("\nПосле повышения:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("\nСравнение дат:");
        System.out.println("Алиса vs Борис: " + emp1.compareDates(emp2));
        System.out.println("Алиса vs Валерий: " + emp1.compareDates(manager));
   
    }
}
