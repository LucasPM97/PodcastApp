package com.example.core.extensions

import com.example.core.models.Episode
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun Episode.durationText(): String {
    if (duration == null) return ""

    val realDuration = duration.toDuration(
        DurationUnit.SECONDS
    )

    realDuration.toComponents { hours, minutes, seconds, _ ->
        if (minutes > 0) {
            val minutesFormated = if (minutes > 9) minutes.toString() else "0$minutes"
            return "$hours:$minutesFormated"
        } else {
            return "${seconds}s"
        }
    }
}

fun Episode.datePublishedText(): String {
    if (datePublished == null) return ""

    val realDuration = datePublished.toDuration(
        DurationUnit.SECONDS
    )

    realDuration.toComponents { days, hours, minutes, _, _ ->
        if (days > 0) return "$days days ago"
        else if (hours > 0) return "$hours hours ago"
        else if (minutes > 4) return "$minutes minutes ago"
        else return "Now"
    }
}

fun Episode.fullWatched(): Boolean {
    if (duration == null || duration == 0) return true
    if (timeWatched == 0) return false

    //Offset in seconds
    val offset = 5

    return duration <= (timeWatched + offset)
}