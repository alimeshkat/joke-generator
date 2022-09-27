package nl.rabobank.online.inkpot

import kotlinx.coroutines.delay
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime


val zoneIdUtc: ZoneId = ZoneId.of("UTC")
val stepInMillis = Duration.ofHours(24).toMillis()
val now: ZonedDateTime get() = ZonedDateTime.now(zoneIdUtc)

val startTime: Long
    get() = ZonedDateTime.of(
        now.year,
        now.month.value,
        now.dayOfMonth,
        8, 0, 0, 0,
        zoneIdUtc
    ).toInstant().toEpochMilli()


suspend inline fun scheduledDaily(task: () -> Unit) {
    while (true) {
        if (now.toInstant().toEpochMilli() > startTime) {
            task()
        }
        delay(stepInMillis)
    }
}
