package com.example.core.extensions

import com.example.core.models.Episode
import kotlin.time.DurationUnit
import kotlin.time.toDuration


fun Episode.durationText(): String {
    if (duration == null) return ""

    return duration.toPlayerDurationText(DurationUnit.SECONDS)
}

fun Episode.datePublishedText(): String {
    if (datePublished == null) return ""

    val realDuration = datePublished.toDuration(
        DurationUnit.SECONDS
    )
    realDuration.toComponents { days, hours, minutes, _, _ ->
        if (days > 365) {
            val years = (days / 365).toInt()
            return "$years years ago"
        }
        if (days > 30) {
            val months = (days / 30).toInt()
            return "$months months ago"
        }
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