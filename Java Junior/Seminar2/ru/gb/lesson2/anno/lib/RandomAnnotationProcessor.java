package ru.gb.lesson2.anno.lib;

import ru.gb.lesson2.hw.Homework;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class RandomAnnotationProcessor {

    public static void processAnnotation(Object obj) {
        java.util.Random random = new java.util.Random();
        Class<?> objClass = obj.getClass();

        for (Field field : objClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Random.class) && field.getType().isAssignableFrom(int.class)) {
                Random annotation = field.getAnnotation(Random.class);
                int min = annotation.min();
                int max = annotation.max();

                try {
                    field.setAccessible(true); // чтобы можно было изменять финальные поля
                    field.set(obj, random.nextInt(min, max));
                } catch (IllegalAccessException e) {
                    System.err.println("Не удалось вставить значение в поле: " + e.getMessage());
                }
            }

            if (field.isAnnotationPresent(Homework.RandomDate.class)) {
                Homework.RandomDate randomDate = field.getAnnotation(Homework.RandomDate.class);
                long min = randomDate.min();
                long max = randomDate.max();

                if (min >= max) {
                    throw new IllegalArgumentException("min должен быть больше max в @RandomDate annotation");
                }

                long randomTime = min + (long) (random.nextDouble() * (max - min));
                ZoneId zoneId = ZoneId.of(randomDate.zone());

                try {
                    field.setAccessible(true);

                    if (Date.class.isAssignableFrom(field.getType())) {
                        Date randomDateValue = new Date(randomTime);
                        field.set(obj, randomDateValue);
                    } else if (Instant.class.isAssignableFrom(field.getType())) {
                        Instant randomInstant = Instant.ofEpochMilli(randomTime);
                        field.set(obj, randomInstant);
                    } else if (LocalDate.class.isAssignableFrom(field.getType())) {
                        LocalDate randomLocalDate = Instant.ofEpochMilli(randomTime).atZone(zoneId).toLocalDate();
                        field.set(obj, randomLocalDate);
                    } else if (LocalDateTime.class.isAssignableFrom(field.getType())) {
                        LocalDateTime randomLocalDateTime = Instant.ofEpochMilli(randomTime).atZone(zoneId).toLocalDateTime();
                        field.set(obj, randomLocalDateTime);
                    } else {
                        System.err.println("Неподдерживаемый тип поля: " + field.getType());
                    }
                } catch (IllegalAccessException e) {
                    System.err.println("Не удалось вставить значение в поле: " + e.getMessage());
                }
            }
        }
    }
}
