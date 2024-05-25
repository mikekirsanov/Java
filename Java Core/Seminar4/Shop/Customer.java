package Shop;

public class Customer {
    enum Genders {
        MALE, FEMALE
    };

    private String name;
    private int age;
    private String phone;
    private Genders gender;

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    public Customer(String name, int age, String phone, Genders gender) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + '\'' + ", age=" + age + ", phone=" + phone + "'}";
    }
}
