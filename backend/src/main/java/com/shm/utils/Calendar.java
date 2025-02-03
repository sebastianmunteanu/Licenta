package com.shm.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class Calendar {

    public static final LocalDateTime NOW = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

    public static LocalDateTime startDay(int value) {
        return LocalDateTime.of(LocalDate.now().minusDays(value), LocalTime.MIN);
    }

    public List<String> getMonths() {
        List<String> weekCharacters = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            weekCharacters.addFirst(LocalDate.now().minusMonths(i).getMonth()
                    .getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ro-RO")).toUpperCase()
                    .substring(0, 3));
        }

        return weekCharacters;
    }

    public List<Character> getWeekDays() {
        List<Character> weekCharacters = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            weekCharacters.addFirst(LocalDate.now().minusDays(i).getDayOfWeek()
                    .getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ro-RO")).toUpperCase().charAt(0));
        }

        return weekCharacters;
    }

}
