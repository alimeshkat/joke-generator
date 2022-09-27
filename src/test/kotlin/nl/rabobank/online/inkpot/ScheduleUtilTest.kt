package nl.rabobank.online.inkpot

import kotlinx.coroutines.runBlocking
import org.junit.Test

class ScheduleUtilTest {

    @Test
    fun test() = runBlocking {
        scheduledDaily {
            println("hello")
        }
    }

}
