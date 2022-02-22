package com.htg.test;


import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TestDemo {

    @Test
    public void test01() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        String format = now.format(DateTimeFormatter.ISO_DATE);
        String format1 = now.format(DateTimeFormatter.ISO_DATE_TIME);
        String format2 = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
        System.out.println(format1);
        System.out.println(format2);


        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println(dayOfWeek.plus(5).getValue());


        LocalDate parse = LocalDate.parse("2021-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate parse2 = LocalDate.parse("2021-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(parse);
        boolean equal = parse.isEqual(parse2);
        System.out.println(equal);


        LocalDateTime time1 = LocalDateTime.parse("2021-01-01 12:13:24", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime time2 = LocalDateTime.parse("2021-01-02 12:14:24", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Duration between1 = Duration.between(time1, time2);
        System.out.println(between1);
        long l = between1.toDays();
        long l1 = between1.toHours();
        long l2 = between1.toMinutes();
        long seconds = between1.getSeconds();

        System.out.println(l + " " + l1 + " " + l2 + " " + seconds);


        Duration between = Duration.between(now, now.minusDays(2));
        System.out.println(between);

    }

}
