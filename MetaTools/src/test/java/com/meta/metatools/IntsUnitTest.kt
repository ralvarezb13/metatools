package com.meta.metatools

import android.util.Log
import com.meta.metatools.numbers.getMonth
import org.junit.Assert
import org.junit.Test

class IntsUnitTest {

    @Test
    fun getMonth() {
        Assert.assertEquals("enero", 0.getMonth())
    }
}