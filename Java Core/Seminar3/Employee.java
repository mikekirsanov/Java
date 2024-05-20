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

    public int compareDates(Employee other) {
        int[] thisDate = {this.year, this.month, this.day};
        int[] otherDate = {other.year, other.month, other.day};

        for (int i = 0; i < 3; i++) {
            int comparison = Integer.compare(thisDate[i], otherDate[i]);
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
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
