package nl.rabobank.online.inkpot

import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

class ScheduleUtil(
    val duration: Duration = Duration.ofDays(1),
    private val startingTime: LocalTime = LocalTime.of(8, 0, 0, 0)
) {
    private val zoneIdUtc: ZoneId = ZoneId.of("UTC")
    val now: ZonedDateTime get() = ZonedDateTime.now(zoneIdUtc)

    val setupDateTime: ZonedDateTime
        get() {
            val now1 = now
            return ZonedDateTime.of(
                LocalDate.of(now1.year, now1.month.value, now1.dayOfMonth),
                startingTime,
                zoneIdUtc
            )
        }

    suspend inline fun schedule(task: () -> Unit) {
        val startTime = setupDateTime
        var startTimeMillis = startTime.toInstant().toEpochMilli()
        var endTimeMillis = startTime.plus(duration).toInstant().toEpochMilli()

        println("startTime = $startTimeMillis ")
        println("endTime = $endTimeMillis ")
        println("now = ${now.toInstant().toEpochMilli()} ")
        println("diff ${startTimeMillis - now.toInstant().toEpochMilli()}")
        println("diff ${endTimeMillis - now.toInstant().toEpochMilli()}")

        while (true) {

            val now1 = now
            val nowMillis = now1.toInstant().toEpochMilli()
            val diffStartTimeCurrentTime = nowMillis - startTimeMillis
//        println("diff start time $startTimeMillis and $nowMillis = $diffStartTimeCurrentTime")

            if (diffStartTimeCurrentTime in 1..999) {

                var checkTaskExecution = true
                do {
//                println("diff end time  ${now.toInstant().toEpochMilli() - endTimeMillis} ")

                    if (now.toInstant().toEpochMilli() - endTimeMillis in 1..999) {

                        task()

                        val newEndTimeMillis = now1.plus(duration).toInstant().toEpochMilli()
                        val delay = newEndTimeMillis - nowMillis

                        println("delay = $delay")

                        delay(delay)

                        startTimeMillis = setupDateTime.toInstant().toEpochMilli()
                        endTimeMillis = now.plus(duration).toInstant().toEpochMilli()
                        checkTaskExecution = false
                    }
                } while (checkTaskExecution)
            }
            delay(500)

        }

    }

}
