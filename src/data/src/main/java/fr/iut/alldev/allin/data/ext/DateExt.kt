package fr.iut.alldev.allin.data.ext

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun ZonedDateTime.formatToMediumDate(): String {
    return this.format(
        DateTimeFormatter.ofPattern("dd MMM yyyy")
    ).replaceFirstChar { it.uppercase() }
}

fun ZonedDateTime.formatToTime(): String {
    return this.format(
        DateTimeFormatter.ofPattern("hh:mm")
    ).replaceFirstChar { it.uppercase() }
}