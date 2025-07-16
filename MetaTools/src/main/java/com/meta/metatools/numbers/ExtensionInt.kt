package com.meta.metatools.numbers

import android.content.res.Resources
import java.text.SimpleDateFormat
import java.util.*


fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.getMonth(): String {
    val month = this
    return Calendar.getInstance(Locale.forLanguageTag("es")).apply {
        set(Calendar.MONTH, month)
    }.let {
        SimpleDateFormat("MMMM", Locale.getDefault()).format(it.time)
    }
}

fun Int.getRangesInWeek(): String {
    val week = this
    val firstDateOfThatWeek = Calendar.getInstance(Locale.forLanguageTag("es")).apply {
        set(Calendar.WEEK_OF_YEAR, week)
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    }.let {
        SimpleDateFormat("d MMMM", Locale.getDefault()).format(it.time)
    }
    val lastDateOfThatWeek = Calendar.getInstance(Locale.forLanguageTag("es")).apply {
        set(Calendar.WEEK_OF_YEAR, week)
        set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    }.let {
        SimpleDateFormat("d MMMM", Locale.getDefault()).format(it.time)
    }
    return "$firstDateOfThatWeek - $lastDateOfThatWeek"
}
