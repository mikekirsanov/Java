package ru.gb.lesson2.hw;

import ru.gb.lesson2.anno.lib.ObjectCreator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Homework {

    /**
     * В существующий класс ObjectCreator добавить поддержку аннотации RandomDate (по аналогии с Random):
     * 1. Аннотация должна обрабатываться только над полями типа java.util.Date
     * 2. Проверить, что min < max
     * 3. В поле, помеченном аннотацией, нужно вставлять рандомную дату,
     * UNIX-время которой находится в диапазоне (min, max)
     * 4. Добавить поддержку для типов Instant, ...
     * 5. Добавить атрибут Zone и поддержку для типов LocalDate, LocalDateTime
     */

    /**
     * Примечание:
     * Unix-время - количество милисекунд, прошедших с 1 января 1970 года по UTC-0.
     */

    // FIXME: Заставить аннотацию ставиться только над полями
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface RandomDate {

        long min() default 1704067200000L; // 1 января 2024 года UTC0

        long max() default 1735689600000L; // 1 января 2025 года UTC0

        String zone() default "UTC";
    }

    @RandomDate(min = 1704067200000L, max = 1735689600000L)
    private Date randomDateField;

    @RandomDate(min = 1704067200000L, max = 1735689600000L)
    private Instant randomInstantField;

    @RandomDate(min = 1704067200000L, max = 1735689600000L, zone = "Europe/Moscow")
    private LocalDate randomLocalDateField;

    @RandomDate(min = 1704067200000L, max = 1735689600000L, zone = "Europe/Moscow")
    private LocalDateTime randomLocalDateTimeField;

    public static void main(String[] args) {
        Homework hw = ObjectCreator.createObj(Homework.class);
        if (hw != null) {
            System.out.println(hw.randomDateField);
            System.out.println(hw.randomInstantField);
            System.out.println(hw.randomLocalDateField);
            System.out.println(hw.randomLocalDateTimeField);
        } else {
            System.err.println("Ошибка создания объекта Homework");
        }
    }
}
