package Homework;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Handbook {
    private List<Employee> handbook;

    public Handbook() {
        this.handbook = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        handbook.add(employee);
    }

    public List<Employee> findEmployeesBySeniority(int seniority) {
        return handbook.stream()
                .filter(employee -> employee.getSeniority() == seniority)
                .collect(Collectors.toList());
    }

    public List<String> getPhoneNumbersByName(String name) {
        return handbook.stream()
                .filter(employee -> employee.getName().equals(name))
                .map(Employee::getPhonenumber)
                .collect(Collectors.toList());
    }

    public Employee findEmployeeByNumber(int number) {
        return handbook.stream()
                .filter(employee -> employee.getNumber() == number)
                .findFirst()
                .orElse(null);
    }
}
