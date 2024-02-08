package ru.lavafrai.maiapp.data

import ru.lavafrai.maiapp.data.models.schedule.OneDaySchedule
import ru.lavafrai.maiapp.data.models.schedule.OneWeekSchedule
import ru.lavafrai.maiapp.data.models.schedule.ScheduleLesson
import ru.lavafrai.maiapp.data.models.schedule.ScheduleLessonType
import ru.lavafrai.maiapp.data.models.schedule.ScheduleWeekId
import java.time.DayOfWeek

fun getSampleWeekSchedule(): OneWeekSchedule {
    return OneWeekSchedule(
        ScheduleWeekId(
            0,
            rangeLabel = "testWeek - testWeekEnd"
        ),
        listOf(
            getSampleDaySchedule(),
            getSampleDaySchedule()
        )
    )
}

fun getSampleDaySchedule(): OneDaySchedule {
    return OneDaySchedule(
        listOf(
            ScheduleLesson(
                "Иностранный язык",
                "14:45 – 16:15",
                ScheduleLessonType.SEMINAR,
                "Христофорова Наталья Игоревна",
                "Орш. В-301"
            )
        ),
        DayOfWeek.SATURDAY,
        "12 февраля"
    )
}

fun getSampleLessonSchedule(): ScheduleLesson {
    return ScheduleLesson(
                "Иностранный язык",
                "14:45 – 16:15",
                ScheduleLessonType.SEMINAR,
                "Христофорова Наталья Игоревна",
                "Орш. В-301"
    )
}