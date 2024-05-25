package Parties;

public class Employee {

    public enum Genders {
        MALE, FEMALE
    }

    String name;
    String midName;
    String surName;
    String phone;
    String position;
    int salary;
    int birth;
    Genders gender;

    public Employee(String name, String midName, String surName, String phone, String position, int salary, int birth, Genders gender) {
        this.name = name;
        this.midName = midName;
        this.surName = surName;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.birth = birth;
        this.gender = gender;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }
}
