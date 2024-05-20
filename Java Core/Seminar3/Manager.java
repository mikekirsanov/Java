public class Manager extends Employee {
    public Manager(String name, int year, int month, int day, double salary) {
        super(name, year, month, day, salary);
    }

    @Override
    public String toString() {
        return "Руководитель" + super.toString().substring(8);
    }
}
