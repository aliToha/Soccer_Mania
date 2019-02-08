package com.alimuthohhari.premierleagueschedule

import com.alimuthohhari.premierleagueschedule.DateUtils.toSimpleString
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class DateUtilsTest {
    @Test
    fun testToSimpleString() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed,28 Feb 2018", toSimpleString(date))
    }
}