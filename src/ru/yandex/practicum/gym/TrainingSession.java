package ru.yandex.practicum.gym;

public class TrainingSession {

    //группа
    private Group group;
    //тренер
    private Coach coach;
    //день недели
    private DayOfWeek dayOfWeek;
    //время начала занятия
    private TimeOfDay timeOfDay;

    public TrainingSession(Group group, Coach coach, DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        this.group = group;
        this.coach = coach;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
    }

    public Group getGroup() {
        return this.group;
    }

    public Coach getCoach() {
        return this.coach;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return this.timeOfDay;
    }
}
