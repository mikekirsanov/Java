class Employee {
    private String name;
    private int year;
    private int month;
    private int day;
    private double salary;

    public Employee(String name, int year, int month, int day, double salary) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.salary = salary;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Работник " + name + " - Заработная плата: " + salary;
    }
}
