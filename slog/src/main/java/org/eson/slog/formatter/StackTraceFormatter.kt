package org.eson.slog.formatter

import java.lang.StringBuilder

class StackTraceFormatter : SLogFormatter<Array<StackTraceElement?>?> {
    override fun format(data: Array<StackTraceElement?>?): String {
        data ?: return ""
        if (data.isEmpty()) {
            return ""
        }
        if (data.size == 1) {
            return data[0].toString()
        }

        val sb = StringBuilder()
        var i = 0
        val len = data.size
        while (i < len) {
            if (i == 0) {
                sb.append("stackTrace info: \n")
            }
            if (i != len - 1) {
                sb.append(data[i].toString())
                sb.append("\n")
            } else {
                sb.append(data[i].toString())
            }
            i++
        }
        return sb.toString()
    }
}