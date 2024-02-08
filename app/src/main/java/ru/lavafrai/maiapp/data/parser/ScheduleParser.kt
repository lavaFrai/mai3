package ru.lavafrai.maiapp.data.parser

import org.jsoup.nodes.Element
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.data.models.schedule.OneDaySchedule
import ru.lavafrai.maiapp.data.models.schedule.OneWeekSchedule
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.data.models.schedule.ScheduleLesson
import ru.lavafrai.maiapp.data.models.schedule.ScheduleLessonType
import ru.lavafrai.maiapp.data.models.schedule.ScheduleWeekId
import ru.lavafrai.maiapp.data.models.schedule.network.getSchedulePage
import ru.lavafrai.maiapp.data.models.schedule.parseScheduleWeek
import ru.lavafrai.maiapp.utils.mapThreaded
import java.time.DayOfWeek


fun parseSchedule(groupId: GroupId) : Schedule {
    val weeks = parseScheduleParseWeeks(groupId)

    val schedules = weeks.mapThreaded {
        parseScheduleParseWeek(groupId, it)
    }

    return Schedule(groupId, schedules);
}


fun parseScheduleParseWeek(groupId: GroupId, weekId: ScheduleWeekId): OneWeekSchedule {
    val page = getSchedulePage(mapOf("group" to groupId.name, "week" to weekId.number.toString()))

    val lessons = page.select(".step").select(".step-content").map { subParseOneDaySchedule(it) }
    return OneWeekSchedule(weekId, lessons)
}

fun subParseOneDaySchedule(page: Element): OneDaySchedule {
    val day = page.select(".step-title").text()
    val dayOfWeek = when {
        day.startsWith("Пн") -> DayOfWeek.MONDAY
        day.startsWith("Вт") -> DayOfWeek.TUESDAY
        day.startsWith("Ср") -> DayOfWeek.WEDNESDAY
        day.startsWith("Чт") -> DayOfWeek.THURSDAY
        day.startsWith("Пт") -> DayOfWeek.FRIDAY
        day.startsWith("Сб") -> DayOfWeek.SUNDAY
        day.startsWith("Вс") -> DayOfWeek.SATURDAY
        else -> DayOfWeek.SATURDAY
    }
    val date = day.subSequence(4, day.length) as String
    val lessons = page.select(".step-content > div").map { subParseLesson(it) }

    return OneDaySchedule(lessons, dayOfWeek, date)
}

fun subParseLesson(page: Element): ScheduleLesson {
    val type = when (page.select(".badge").text()) {
        "ЛК" -> ScheduleLessonType.LECTURE
        "ПЗ" -> ScheduleLessonType.SEMINAR
        "ЛР" -> ScheduleLessonType.LABORATORY
        else -> ScheduleLessonType.Unknown
    }
    val name = page.child(0).text().removeSuffix(" ${page.select(".badge").text()}")
    val timeRange = page.child(1).child(0).text()

    val teacher: String
    val location: String
    if (page.child(1).children().size == 3) {
        teacher = page.child(1).child(1).text()
        location = page.child(1).child(2).text()
    } else {
        teacher = ""
        location = page.child(1).child(1).text()
    }

    return ScheduleLesson(name, timeRange, type, teacher, location)
}

fun parseScheduleParseWeeks(groupId: GroupId): List<ScheduleWeekId> {
    val page = getSchedulePage(mapOf("group" to groupId.name))

    return page.select("#collapseWeeks").select(".list-group-item").map { parseScheduleWeek(it.text()) }
}
