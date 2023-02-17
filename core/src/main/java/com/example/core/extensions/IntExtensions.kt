package com.example.core.extensions

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun Int.toPlayerDurationText(durationUnit: DurationUnit): String =
    this.toDuration(durationUnit)
        .getDurationText()

fun Long.toPlayerDurationText(durationUnit: DurationUnit): String =
    this.toDuration(durationUnit)
        .getDurationText()

private fun Duration.getDurationText(): String {
    toComponents { hours, minutes, seconds, _ ->
        return if (hours > 0) {
            addCeroOnFrontIfIsNeeded(hours.toInt()) + ":"
        } else {
            ""
        } +
                addCeroOnFrontIfIsNeeded(minutes) + ":" +
                addCeroOnFrontIfIsNeeded(seconds)

    }
}

private fun addCeroOnFrontIfIsNeeded(time: Int): String {
    val isNeeded = time <= 9
    return if (isNeeded) "0$time"
    else time.toString()
}