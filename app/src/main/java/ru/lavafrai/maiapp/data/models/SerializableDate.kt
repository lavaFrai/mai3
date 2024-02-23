package ru.lavafrai.maiapp.data.models

import android.content.Context
import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.utils.toLocalizedMonthString
import java.util.Calendar


@Serializable
data class SerializableDate (
    val year: Int = 0,
    val month: Short = 0,
    val day: Short = 0,
) {
    fun isLaterThan(another: SerializableDate): Boolean {
        return if (year == another.year) {
            if (month == another.month) {
                day > another.day
            } else month > another.month
        } else year > another.year
    }

    fun isEarlierThan(another: SerializableDate): Boolean {
        return !(isLaterThan(another) || isSame(another))
    }

    fun isSame(another: SerializableDate): Boolean {
        return  year == another.year &&
                month == another.month &&
                day == another.day
    }

    operator fun compareTo(another: SerializableDate): Int {
        return when {
            isSame(another) -> 0
            isLaterThan(another) -> 1
            isEarlierThan(another) -> -1
            else -> 0
        }
    }

    override fun toString(): String {
        return "${day.toString().padStart(2, '0')}.${month.toString().padStart(2, '0')}.$year"
    }

    fun toLocalizedDayMonthString(context: Context): String {
        return "${day.toString().padStart(2, '0')} ${(month.toInt() - 1).toLocalizedMonthString(context.resources)}"
    }

    companion object {
        fun now(): SerializableDate {
            val calendar = Calendar.getInstance()
            return SerializableDate(
                calendar.get(Calendar.YEAR),
                (calendar.get(Calendar.MONTH) + 1).toShort(),
                calendar.get(Calendar.DAY_OF_MONTH).toShort(),
            )
        }

        fun parse(string: String): SerializableDate {
            val match = "(\\d{2})\\.(\\d{2})\\.(\\d{4})".toRegex().find(string)!!
            return SerializableDate(
                match.groups[3]!!.value.toInt(),
                match.groups[2]!!.value.toShort(),
                match.groups[1]!!.value.toShort(),
            )
        }

        fun of(calendar: Calendar): SerializableDate {
            return SerializableDate(
                year = calendar.get(Calendar.YEAR),
                month = (calendar.get(Calendar.MONTH) + 1).toShort(),
                day = calendar.get(Calendar.DAY_OF_MONTH).toShort(),
            )
        }

        fun ofYearLess(calendar: Calendar): SerializableDate {
            return SerializableDate(
                month = (calendar.get(Calendar.MONTH) + 1).toShort(),
                day = calendar.get(Calendar.DAY_OF_MONTH).toShort(),
            )
        }
    }
}


fun main() {
    println(SerializableDate.parse("09.02.2024").toString())
}
