package org.eson.slog

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

internal data class SLogBean(
    val leave: Int,
    val tag: String,
    val log: String,
    val timeMillis: Long = System.currentTimeMillis(),
) {
    private val sdf = SimpleDateFormat("HH:mm:ss SSS", Locale.CHINA)
    fun flattenedLog(): String {
        return "${getFlattened()}\n${log}"
    }

    fun getFlattened(): String {
        return format(timeMillis) + '/' + getLeaveDes() + '/' + tag + ':'
    }

    private fun format(timeMillis: Long): String {
        return sdf.format(timeMillis)
    }

    private fun getLeaveDes(): Char {
        return when (leave) {
            Log.VERBOSE -> 'V'
            Log.DEBUG -> 'D'
            Log.INFO -> 'I'
            Log.WARN -> 'W'
            Log.ERROR -> 'E'
            else ->'A'
        }
    }
}
