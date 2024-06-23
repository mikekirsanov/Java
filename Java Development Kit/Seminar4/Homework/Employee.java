package Homework;

public class Employee {
    private static int counter = 0;
    private final int number;
    private final String phonenumber;
    private final String name;
    private final int seniority;

    public Employee(String phonenumber, String name, int seniority) {
        this.number = ++counter;
        this.phonenumber = phonenumber;
        this.name = name;
        this.seniority = seniority;
    }

    public int getNumber() {
        return number;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getName() {
        return name;
    }

    public int getSeniority() {
        return seniority;
    }

    @Override
    public String toString() {
        return "Сотрудник {" +
                "табельный номер = " + number +
                ", номер телефона = '" + phonenumber + '\'' +
                ", имя = '" + name + '\'' +
                ", стаж = " + seniority +
                '}';
    }
}