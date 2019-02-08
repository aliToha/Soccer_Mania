package com.alimuthohhari.premierleagueschedule

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.content.ContextCompat.getSystemService
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

object DateUtils {
    fun toSimpleString(date: Date): String {
        val formater = SimpleDateFormat("EEE,d MMM yyyy", Locale.US)
        return formater.format(date)
    }
}

object StringUtils {
    fun toSimpleString(date: String): Date {
        val formater = SimpleDateFormat("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.US)
        return formater.parse(date)
    }
}

object timeCalnder {
    fun toSimpleString(date: String): Date {
        val formater = SimpleDateFormat("HH:mm:ss", Locale.US)
        return formater.parse(date)
    }
}

object timeCalnder2 {
    fun toSimpleString(date: String): Date {
        val formater = SimpleDateFormat("HH:mm", Locale.US)
        return formater.parse(date)
    }
}

object calenderTime {
    fun toSimpleString(date: Date): String {
        val formater = SimpleDateFormat("h:mm a", Locale.US)
        return formater.format(date)
    }
}

object TimeStringMilis {
    fun toSimpleString(date: String): Date {
        val formater = SimpleDateFormat("EEE,d MMM yyyy HH:mm:ss+00:00", Locale.US)
        return formater.parse(date)
    }
}

