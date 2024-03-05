package HomeTask;
/*Задание: Реализовать в java:
- Создать множество ноутбуков.
- Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:
“Введите цифру, соответствующую необходимому критерию: 1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
-Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно в Map.
-Отфильтровать ноутбуки из первоначального множества и вывести подходящие по условиям. */

import java.util.*;

class Laptops {
    private String model;
    private String brand;
    private int ram;
    private int storageGB;
    private String operatingSystem;
    private String color;
    private double screenSize;

    public Laptops(String model, String brand, int ram, int storageGB, String operatingSystem, String color,
            double screenSize) {
        this.model = model;
        this.brand = brand;
        this.ram = ram;
        this.storageGB = storageGB;
        this.operatingSystem = operatingSystem;
        this.color = color;
        this.screenSize = screenSize;
    }
    
    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorageGB() {
        return storageGB;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public double getScreenSize() {
        return screenSize;
    }

    // Сеттеры
    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setStorageGB(int storageGB) {
        this.storageGB = storageGB;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public boolean matchesFilterCriteria(Map<Integer, Object> filterCriteria) {
        for (Map.Entry<Integer, Object> entry : filterCriteria.entrySet()) {
            int filterKey = entry.getKey();
            Object filterValue = entry.getValue();

            switch (filterKey) {
                case 1:
                    if (ram < Integer.parseInt(filterValue.toString())) {
                        return false;
                    }
                    break;
                case 2:
                    if (storageGB < Integer.parseInt(filterValue.toString())) {
                        return false;
                    }
                    break;
                case 3:
                    if (!operatingSystem.equalsIgnoreCase(filterValue.toString())) {
                        return false;
                    }
                    break;
                case 4:
                    if (!color.equalsIgnoreCase(filterValue.toString())) {
                        return false;
                    }
                    break;
                case 5:
                    if (screenSize < Double.parseDouble(filterValue.toString())) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storageGB=" + storageGB +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", color='" + color + '\'' +
                ", screenSize=" + screenSize +
                '}';
    }
}