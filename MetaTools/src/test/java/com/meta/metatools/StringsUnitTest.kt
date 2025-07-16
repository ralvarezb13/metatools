package com.meta.metatools

import com.meta.metatools.strings.*
import org.junit.Assert
import org.junit.Test
import kotlin.text.count

class StringsUnitTest {

    @Test
    fun getBankName() {
        Assert.assertEquals("SANTANDER", "014".getBankName())
    }

    @Test
    fun getInitials() {
        Assert.assertEquals("AG", "Arturo Garcia Rivera".getInitials())
    }

    @Test
    fun fetchVerificationCode() {
        Assert.assertEquals(6, "000000".fetchVerificationCode().count())
    }

    @Test
    fun capitalizeWords() {
        Assert.assertEquals("Arturo G R", "arturo g r".capitalizeWords())
    }



}