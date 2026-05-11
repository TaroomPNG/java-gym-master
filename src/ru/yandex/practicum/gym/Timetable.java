package ru.yandex.practicum.gym;

import ru.yandex.practicum.gym.service.TimeOfDayComparator;

import java.util.*;

public class Timetable {

    private static final Comparator<TimeOfDay> TIME_OF_DAY_COMPARATOR = new TimeOfDayComparator();

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new EnumMap<>(
            DayOfWeek.class
    );

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        // Заметки для себя: Тут, мы смотрим есть ли в мапе такой день
        if (timetable.containsKey(dayOfWeek)) {
            Map<TimeOfDay, List<TrainingSession>> trainingMap = timetable.get(dayOfWeek);

            // Если такой день есть, смотрим есть ли такое время.
            if (trainingMap.containsKey(timeOfDay)) {
                List<TrainingSession> trainingList = trainingMap.get(timeOfDay);
                trainingList.add(trainingSession);
            } else {
                trainingMap.put(timeOfDay, new ArrayList<>(List.of(trainingSession)));
            }
        } else {
            TreeMap<TimeOfDay, List<TrainingSession>> trainingMap = new TreeMap<>(TIME_OF_DAY_COMPARATOR);
            trainingMap.put(timeOfDay, new ArrayList<>(List.of(trainingSession)));

            timetable.put(dayOfWeek, trainingMap);
        }
    }

    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (timetable.containsKey(dayOfWeek)) {
            return timetable.get(dayOfWeek);
        } else System.out.println("В день " + dayOfWeek + " тренировок нет");
        return new TreeMap<>(TIME_OF_DAY_COMPARATOR);
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        if (timetable.containsKey(dayOfWeek)) {
            if (timetable.get(dayOfWeek).containsKey(timeOfDay)) {
                return timetable.get(dayOfWeek).get(timeOfDay);
            } else System.out.println("В " + timeOfDay + " тренировок нет");
            return new ArrayList<>();
        } else System.out.println("В день " + dayOfWeek + " тренировок нет");
        return new ArrayList<>();
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> coachTrainingsCounterMap = new HashMap<>();
        for (TreeMap<TimeOfDay, List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessions : dayMap.values()) {
                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    coachTrainingsCounterMap.put(coach,
                            coachTrainingsCounterMap.getOrDefault(coach, 0) + 1);
                }
            }
        }

        List<CounterOfTrainings> coachTrainingsCounterList = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : coachTrainingsCounterMap.entrySet()) {
            coachTrainingsCounterList.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }
        coachTrainingsCounterList.sort(new Comparator<CounterOfTrainings>() {
            @Override
            public int compare(CounterOfTrainings o1, CounterOfTrainings o2) {
                return Integer.compare(o2.getCount(), o1.getCount());
            }
        });
        return coachTrainingsCounterList;
    }
}