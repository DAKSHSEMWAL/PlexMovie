package com.dakshsemwal.plaxmoview.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    const val DATE_dd_MMM_yyyy = "dd MMM, yyyy"
    const val DATE_YYYY_MM_dd = "yyyy-MM-dd"
}
fun Date.getTimeDuration(): String {
    val minutes = this.time.rem(60)
    val hour = TimeUnit.MINUTES.toHours(this.time)
    return "${hour}h${minutes}m"
}
/**
 * This function converts Date from one format to another
 */
fun getFormattedDate(inputFormat: String, outputFormat: String, selectedDate: String): String? {
    return try {
        val parseDateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
        val date = parseDateFormat.parse(selectedDate)
        val requiredDateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
        date?.let {
            return requiredDateFormat.format(date)
        }
    } catch (e: ParseException) {
        e.printStackTrace()
        return null
    }
}