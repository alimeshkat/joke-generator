package nl.rabobank.online.inkpot

import kotlinx.coroutines.delay
import java.time.ZoneId
import java.time.ZonedDateTime


val zoneIdUtc: ZoneId = ZoneId.of("UTC")
val now: ZonedDateTime get() = ZonedDateTime.now(zoneIdUtc)

val setupTime: ZonedDateTime
    get() {
        val now1 = now
        return ZonedDateTime.of(
            now1.year,
            now1.month.value,
            now1.dayOfMonth,
            8, 0, 0, 0,
            zoneIdUtc
        )
    }


const val duration: Long = 5

suspend inline fun scheduledDaily(task: () -> Unit) {
    var startTime = setupTime
    var endTime = startTime.plusSeconds(duration).toInstant().toEpochMilli()
    while (true) {
        val toEpochMilli = startTime.toInstant().toEpochMilli()
        println("now = $toEpochMilli")
        println("endTime = $endTime ")
        if (toEpochMilli >= endTime) {
            task()
        }
        val newEndTime = startTime.plusDays(duration).toInstant().toEpochMilli()
        val delay = newEndTime - startTime.toInstant().toEpochMilli()
        println("delay = $delay")
        delay(delay)
        startTime = setupTime
        endTime = startTime.plusDays(duration).toInstant().toEpochMilli()
    }
}
