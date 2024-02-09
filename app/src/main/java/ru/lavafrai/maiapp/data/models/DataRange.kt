package ru.lavafrai.maiapp.data.models

import kotlinx.serialization.Serializable


@Serializable
class DateRange(
    val startDate: SerializableDate,
    val endDate: SerializableDate,
) {
    operator fun contains(another: SerializableDate): Boolean {
        return startDate <= another && endDate >= another
    }


    override fun toString(): String {
        return "${startDate.toString()} - ${endDate.toString()}"
    }


    companion object {
        fun parse(string: String): DateRange {
            return DateRange(
                SerializableDate.parse(string.split(" - ")[0]),
                SerializableDate.parse(string.split(" - ")[1]),
            )
        }
    }
}
