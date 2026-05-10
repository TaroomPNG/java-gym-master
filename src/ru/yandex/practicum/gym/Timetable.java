package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

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
        }
        else {
            TreeMap<TimeOfDay, List<TrainingSession>> trainingMap = new TreeMap<>(
                    new Comparator<TimeOfDay>() {
                @Override
                public int compare(TimeOfDay o1, TimeOfDay o2) {
                    if (o1.getHours() == o2.getHours()) {
                        return o1.getMinutes() - o2.getMinutes();
                    } else
                        return o1.getHours() - o2.getHours();
                }
            });
            trainingMap.put(timeOfDay, new ArrayList<>(List.of(trainingSession)));

            timetable.put(dayOfWeek, trainingMap);
        }
    }

    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (timetable.containsKey(dayOfWeek)) {
            return timetable.get(dayOfWeek);
        }
        else System.out.println("В день " + dayOfWeek + " тренировок нет");
        return null;
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        if (timetable.containsKey(dayOfWeek)) {
            if (timetable.get(dayOfWeek).containsKey(timeOfDay)) {
                return timetable.get(dayOfWeek).get(timeOfDay);
            } else System.out.println("В " + timeOfDay + " тренировок нет");
            return null;
        } else System.out.println("В день " + dayOfWeek + " тренировок нет");
        return null;
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }
}
