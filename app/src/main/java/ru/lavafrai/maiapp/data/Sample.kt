package ru.lavafrai.maiapp.data

import ru.lavafrai.mai.api.models.DateRange
import ru.lavafrai.mai.api.models.SerializableDate
import ru.lavafrai.mai.api.models.schedule.OneDaySchedule
import ru.lavafrai.mai.api.models.schedule.OneWeekSchedule
import ru.lavafrai.mai.api.models.schedule.ScheduleLesson
import ru.lavafrai.mai.api.models.schedule.ScheduleLessonType
import ru.lavafrai.mai.api.models.schedule.ScheduleWeekId
import java.time.DayOfWeek

fun getSampleWeekSchedule(): OneWeekSchedule {
    return OneWeekSchedule(
        ScheduleWeekId(
            0,
            range = DateRange.parse("09.02.2024 - 11.02.2024")
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
        SerializableDate.parse("2024.01.12")
    )
}

fun getSampleLessonSchedule(): ScheduleLesson {
    return ScheduleLesson(
                "Иностранный язык",
                "14:45 – 16:15",
                ScheduleLessonType.SEMINAR,
                "Христофорова Наталья Игоревна / Сычов Михаил Ебанович",
                "Орш. В-301"
    )
}