package ru.yandex.practicum.gym;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;


public class TimetableCoachTest {

    @Test
    void testGetCoachByCoachesForTwoCoaches() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Сергеев", "Генадий", "Харитонович");
        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);

        TrainingSession mondayAdultTrainingSessionCoach1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(20, 0));
        TrainingSession tuesdayAdultTrainingSessionCoach1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.TUESDAY, new TimeOfDay(20,0));

        TrainingSession mondayAdultTrainingSessionCoach2 = new TrainingSession(groupAdult, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(20,0));

        timetable.addNewTrainingSession(mondayAdultTrainingSessionCoach1);
        timetable.addNewTrainingSession(tuesdayAdultTrainingSessionCoach1);
        timetable.addNewTrainingSession(mondayAdultTrainingSessionCoach2);

        // Проверяем, что в списке два эллемента (у нас два тренера)
        assertEquals(2,timetable.getCountByCoaches().size());
        // Проверяем последовательность бедолаг. Благо тут не нужен итератор
        List<CounterOfTrainings> coaches = timetable.getCountByCoaches();

        // Проверяем их объекты
        assertEquals(coach1,coaches.getFirst().getCoach());
        assertEquals(coach2,coaches.getLast().getCoach());
    }

    @Test
    void testGetCoachByCoachesForThreeCoaches() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Сергеев", "Генадий", "Харитонович");
        Coach coach3 = new Coach("Пупа", "Лупа", "Зарплатович");
        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);

        TrainingSession mondayAdultTrainingSessionCoach1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(20, 0));
        TrainingSession tuesdayAdultTrainingSessionCoach1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.TUESDAY, new TimeOfDay(20,0));
        TrainingSession mondayOtherTimeAdultTrainingSessionCoach1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(10,15));

        TrainingSession mondayAdultTrainingSessionCoach2 = new TrainingSession(groupAdult, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(20,0));
        TrainingSession fridayAdultTrainingSessionCoach2 = new TrainingSession(groupAdult, coach2,
                DayOfWeek.FRIDAY, new TimeOfDay(10,15));

        TrainingSession fridayAdultTrainingSessionCoach3 = new TrainingSession(groupAdult, coach3,
                DayOfWeek.FRIDAY, new TimeOfDay(20,0));

        timetable.addNewTrainingSession(mondayAdultTrainingSessionCoach1);
        timetable.addNewTrainingSession(tuesdayAdultTrainingSessionCoach1);
        timetable.addNewTrainingSession(mondayOtherTimeAdultTrainingSessionCoach1);
        timetable.addNewTrainingSession(mondayAdultTrainingSessionCoach2);
        timetable.addNewTrainingSession(fridayAdultTrainingSessionCoach2);
        timetable.addNewTrainingSession(fridayAdultTrainingSessionCoach3);


        // Проверяем, что в списке два эллемента (у нас два тренера)
        assertEquals(3,timetable.getCountByCoaches().size());

        // Проверяем последовательность бедолаг.
        List<CounterOfTrainings> coaches = timetable.getCountByCoaches();

        // Проверяем их объекты
        assertEquals(coach1,coaches.getFirst().getCoach());
        assertEquals(coach2, coaches.get(1).getCoach());
        assertEquals(coach3,coaches.getLast().getCoach());
    }

    @Test
    void testGetCountByCoachesForThreeCoaches() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Сергеев", "Генадий", "Харитонович");
        Coach coach3 = new Coach("Пупа", "Лупа", "Зарплатович");
        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);

        TrainingSession mondayAdultTrainingSessionCoach1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(20, 0));
        TrainingSession tuesdayAdultTrainingSessionCoach1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.TUESDAY, new TimeOfDay(20,0));
        TrainingSession mondayOtherTimeAdultTrainingSessionCoach1 = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(10,15));

        TrainingSession mondayAdultTrainingSessionCoach2 = new TrainingSession(groupAdult, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(20,0));
        TrainingSession fridayAdultTrainingSessionCoach2 = new TrainingSession(groupAdult, coach2,
                DayOfWeek.FRIDAY, new TimeOfDay(10,15));

        TrainingSession fridayAdultTrainingSessionCoach3 = new TrainingSession(groupAdult, coach3,
                DayOfWeek.FRIDAY, new TimeOfDay(20,0));

        timetable.addNewTrainingSession(mondayAdultTrainingSessionCoach1);
        timetable.addNewTrainingSession(tuesdayAdultTrainingSessionCoach1);
        timetable.addNewTrainingSession(mondayOtherTimeAdultTrainingSessionCoach1);
        timetable.addNewTrainingSession(mondayAdultTrainingSessionCoach2);
        timetable.addNewTrainingSession(fridayAdultTrainingSessionCoach2);
        timetable.addNewTrainingSession(fridayAdultTrainingSessionCoach3);

        List<CounterOfTrainings> coaches = timetable.getCountByCoaches();

        // и правильность вычислений
        assertEquals(3, coaches.getFirst().getCount());
        assertEquals(2, coaches.get(1).getCount());
        assertEquals(1, coaches.getLast().getCount());
    }


}
