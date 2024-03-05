package HomeTask;

import java.util.*;

public class LaptopsFilter {
    public static void main(String[] args) {
        Set<Laptops> laptops = new HashSet<>();
        laptops.add(new Laptops("HP15sr32", "HP", 8, 512, "Windows 10", "Silver", 15.6));
        laptops.add(new Laptops("DellXPS15", "Dell", 16, 256, "Ubuntu", "Black", 14.0));
        laptops.add(new Laptops("LenovoThinkPad", "Lenovo", 12, 1000, "Windows 11", "Gray", 13.3));
        laptops.add(new Laptops("AcerAspire", "Acer", 8, 1000, "Dos", "Gray", 17.0));
        laptops.add(new Laptops("AsusROG", "Asus", 16, 2000, "Windows 11", "Black", 15.6));
        laptops.add(new Laptops("MSIGaming", "MSI", 8, 1000, "Dos", "Red", 17.0));
        laptops.add(new Laptops("GigabyteAero", "Gigabyte", 12, 512, "Mint", "Brown", 14.0));
        laptops.add(new Laptops("HP16er34", "HP", 16, 4000, "Windows 10", "Silver", 16.0));

        Scanner scanner = new Scanner(System.in);

        Map<Integer, String> filterCriteria = Map.of(
                1, "Бренд",
                2, "Размер ОЗУ (в GB)",
                3, "Размер накопителя (в GB)",
                4, "Операционная система",
                5, "Размер экрана (в дюймах)"
        );

        try {
            Map<Integer, Object> previousCriteria = new HashMap<>();
            boolean continueFiltering = true;

            while (continueFiltering) {
                int choice;
                do {
                    System.out.println("\nВыберите критерий фильтрации:");
                    for (int i = 1; i <= filterCriteria.size(); i++) {
                        System.out.println(i + " - " + filterCriteria.get(i));
                    }

                    choice = scanner.nextInt();
                    scanner.nextLine();  // Добавленный код для считывания символа новой строки
                } while (!filterCriteria.containsKey(choice));

                Object value;
                if (choice == 5) {
                    System.out.print(filterCriteria.get(choice) + ": ");
                    value = scanner.nextDouble();
                } else {
                    System.out.print(filterCriteria.get(choice) + ": ");
                    value = scanner.nextLine();  // Изменено с next() на nextLine()
                }

                previousCriteria.put(choice, value);
                filterByCriteria(laptops, previousCriteria);

                System.out.print("Продолжить фильтрацию? (1 - да, 2 - нет): ");
                int continueChoice = scanner.nextInt();
                scanner.nextLine();  // Добавленный код для считывания символа новой строки
                continueFiltering = (continueChoice == 1);
            }
        } finally {
            scanner.close();
        }
    }

    private static void filterByCriteria(Set<Laptops> laptops, Map<Integer, Object> criteria) {
        System.out.println("\nРезультаты фильтрации по критериям:");
        boolean found = false;

        for (Laptops laptop : laptops) {
            boolean matchesAllCriteria = true;

            for (Map.Entry<Integer, Object> entry : criteria.entrySet()) {
                int choice = entry.getKey();
                Object value = entry.getValue();

                switch (choice) {
                    case 1:
                        if (!laptop.getBrand().toLowerCase().contains(((String) value).toLowerCase())) {
                            matchesAllCriteria = false;
                        }
                        break;
                    case 2:
                        if (laptop.getRam() < Integer.parseInt((String) value)) {
                            matchesAllCriteria = false;
                        }
                        break;
                    case 3:
                        if (laptop.getStorageSizeGB() < Integer.parseInt((String) value)) {
                            matchesAllCriteria = false;
                        }
                        break;
                    case 4:
                        if (!laptop.getOperatingSystem().toLowerCase().contains(((String) value).toLowerCase())) {
                            matchesAllCriteria = false;
                        }
                        break;
                    case 5:
                        if (laptop.getScreenSize() < (double) value) {
                            matchesAllCriteria = false;
                        }
                        break;
                }
            }

            if (matchesAllCriteria) {
                laptop.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Нет результатов для выбранных критериев.");
        }
    }
}