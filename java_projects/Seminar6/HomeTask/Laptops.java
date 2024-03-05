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

public class Laptops {
    private String model;
    private String brand;
    private int ram;
    private int storageSize;
    private String operatingSystem;
    private String color;
    private double screenSize;

    public Laptops(String model, String brand, int ram, int storageSize, String operatingSystem, String color, double screenSize) {
        this.model = model;
        this.brand = brand;
        this.ram = ram;
        this.storageSize = storageSize;
        this.operatingSystem = operatingSystem;
        this.color = color;
        this.screenSize = screenSize;
    }

    public String getModel() {
        return model;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorageSizeGB() {
        return storageSize;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public boolean matchesFilterCriteria(Map<Integer, Object> criteria) {
        for (Map.Entry<Integer, Object> entry : criteria.entrySet()) {
            int choice = entry.getKey();
            Object value = entry.getValue();

            switch (choice) {
                case 1:
                    if (!getBrand().toLowerCase().equals(((String) value).toLowerCase())) {
                        return false;
                    }
                    break;
                case 2:
                    if (getRam() < Integer.parseInt((String) value)) {
                        return false;
                    }
                    break;
                case 3:
                    if (getStorageSizeGB() < Integer.parseInt((String) value)) {
                        return false;
                    }
                    break;
                case 4:
                    if (!getOperatingSystem().toLowerCase().equals(((String) value).toLowerCase())) {
                        return false;
                    }
                    break;
                case 5:
                    if (getScreenSize() < (double) value) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand +
                ", Model: " + model +
                ", RAM: " + ram + "GB" +
                ", Storage: " + storageSize + "GB" +
                ", OS: " + operatingSystem +
                ", Color: " + color +
                ", Screen Size: " + screenSize + " inches");
    }
}