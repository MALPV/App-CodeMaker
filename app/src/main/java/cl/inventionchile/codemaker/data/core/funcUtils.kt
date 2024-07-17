package cl.inventionchile.codemaker.data.core

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun millisecondsToDate(milliseconds: Long): String {
    val instant = Instant.ofEpochMilli(milliseconds)
    val dateTime = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return dateTime.format(formatter)
}