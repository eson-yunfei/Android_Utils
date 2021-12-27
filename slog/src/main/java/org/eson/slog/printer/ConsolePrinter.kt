package org.eson.slog.printer

import android.util.Log
import org.eson.slog.SLogConfig

internal class ConsolePrinter : SLogPrinter {
    override fun printer(config: SLogConfig, level: Int, tag: String, content: String) {
        val length = content.length
        val countOfSub: Int = length / config.maxSize
        if (countOfSub <= 0) {
            Log.println(level, tag, content)
            return
        }
        var index = 0
        for (i in 0 until countOfSub) {
            Log.println(level, tag, content.substring(index, index + config.maxSize))
            index += config.maxSize
        }
        if (index != length) {
            Log.println(level, tag, content.substring(index, length))
        }
    }

}