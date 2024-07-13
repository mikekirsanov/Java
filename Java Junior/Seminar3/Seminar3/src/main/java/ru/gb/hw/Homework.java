package ru.gb.hw;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Homework {

    /**
     * С помощью JDBC, выполнить следующие пункты:
     * 1. Создать таблицу Person (скопировать код с семинара)
     * 2. Создать таблицу Department (id bigint primary key, name varchar(128) not null)
     * 3. Добавить в таблицу Person поле department_id типа bigint (внешний ключ)
     * 4. Написать метод, который загружает Имя department по Идентификатору person
     * 5. Написать метод, который загружает Map<String, String>, в которой маппинг person.name -> department.name
     * Пример: [{"person #1", "department #1"}, {"person #2", "department #3}]
     * 6. Написать метод, который загружает Map<String, List<String>>, в которой маппинг department.name -> <person.name>
     * Пример:
     * [
     * {"department #1", ["person #1", "person #2"]},
     * {"department #2", ["person #3", "person #4"]}
     * ]
     * <p>
     * 7. Создать классы-обертки над таблицами, и в пунктах 4, 5, 6 возвращать объекты.
     */
    @Getter
    public static class Person {
        private final long id;
        private final String name;
        private int age;
        private boolean active;
        private long departmentId;

        public Person(long id, String name, int age, boolean active, long departmentId) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.active = active;
            this.departmentId = departmentId;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", active=" + active +
                    ", departmentId=" + departmentId +
                    '}';
        }
    }

    @Getter
    public static class Department {
        private final long id;
        private String name;

        public Department(long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                        create table if not exists Department (
                            id bigint primary key,
                            name varchar(128) not null
                        )
                    """);

            statement.execute("""
                        create table if not exists Person (
                            id bigint primary key,
                            name varchar(256),
                            age integer,
                            active boolean,
                            department_id bigint,
                            foreign key (department_id) references Department(id)
                        )
                    """);
        }
    }

    /**
     * Пункт 4
     */
    private static Department getPersonDepartment(Connection connection, long personId) throws SQLException {
        String sql = "SELECT d.id, d.name FROM Department d " +
                "JOIN Person p ON p.department_id = d.id " +
                "WHERE p.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, personId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Department(resultSet.getLong("id"), resultSet.getString("name"));
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * Пункт 5
     */
    private static Map<Person, Department> getPersonDepartments(Connection connection) throws SQLException {
        String sql = "SELECT p.id AS person_id, p.name AS person_name, p.age, p.active, p.department_id, " +
                "d.id AS department_id, d.name AS department_name FROM Person p " +
                "JOIN Department d ON p.department_id = d.id";
        Map<Person, Department> result = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Person person = new Person(resultSet.getLong("person_id"), resultSet.getString("person_name"),
                        resultSet.getInt("age"), resultSet.getBoolean("active"), resultSet.getLong("department_id"));
                Department department = new Department(resultSet.getLong("department_id"), resultSet.getString("department_name"));
                result.put(person, department);
            }
        }
        return result;
    }

    /**
     * Пункт 6
     */
    private static Map<Department, List<Person>> getDepartmentPersons(Connection connection) throws SQLException {
        String sql = "SELECT d.id AS department_id, d.name AS department_name, " +
                "p.id AS person_id, p.name AS person_name, p.age, p.active, p.department_id FROM Department d " +
                "JOIN Person p ON p.department_id = d.id";
        Map<Department, List<Person>> result = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Department department = new Department(resultSet.getLong("department_id"), resultSet.getString("department_name"));
                Person person = new Person(resultSet.getLong("person_id"), resultSet.getString("person_name"),
                        resultSet.getInt("age"), resultSet.getBoolean("active"), resultSet.getLong("department_id"));
                result.computeIfAbsent(department, k -> new ArrayList<>()).add(person);
            }
        }
        return result;
    }

    public static void insertTestData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Department(id, name) VALUES (1, 'Department #1'), (2, 'Department #2'), (3, 'Department #3')");

            statement.executeUpdate("INSERT INTO Person(id, name, age, active, department_id) VALUES " +
                    "(1, 'Person #1', 30, true, 1), " +
                    "(2, 'Person #2', 40, true, 1), " +
                    "(3, 'Person #3', 25, true, 2), " +
                    "(4, 'Person #4', 35, true, 3), " +
                    "(5, 'Person #5', 28, true, 3)");
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test")) {
                createTables(connection);
                insertTestData(connection);

                System.out.println("Имя департамента по идентификатору персоны 1: " + getPersonDepartment(connection, 1));

                Map<Person, Department> personDepartments = getPersonDepartments(connection);
                System.out.println("Сопоставление person -> department: " + personDepartments);

                Map<Department, List<Person>> departmentPersons = getDepartmentPersons(connection);
                System.out.println("Сопоставление department -> <person>: " + departmentPersons);

            } catch (SQLException e) {
                System.err.println("Во время подключения произошла ошибка: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер не найден: " + e.getMessage());
        }
    }
}
