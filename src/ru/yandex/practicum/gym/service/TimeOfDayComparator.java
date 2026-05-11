package ru.yandex.practicum.gym.service;

import ru.yandex.practicum.gym.TimeOfDay;

import java.util.Comparator;

public class TimeOfDayComparator implements Comparator<TimeOfDay> {
    @Override
    public int compare(TimeOfDay o1, TimeOfDay o2) {
        if (o1.getHours() == o2.getHours()) {
            return Integer.compare(o1.getMinutes(), o2.getMinutes());
        }
        return Integer.compare(o1.getHours(), o2.getHours());
    }
}
