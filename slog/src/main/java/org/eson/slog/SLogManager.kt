package org.eson.slog

import org.eson.slog.formatter.StackTraceFormatter
import org.eson.slog.formatter.ThreadFormatter
import org.eson.slog.printer.ConsolePrinter
import org.eson.slog.printer.SLogPrinter
import org.eson.slog.printer.view.SLogViewPrinter
import java.text.SimpleDateFormat
import java.util.*

class SLogManager {

    private var slicingConfig: SLogConfig = SLogConfig()

    init {
        slicingConfig.addPrinter(ConsolePrinter())
    }

    companion object {

        val sLogManager by lazy { SLogManager() }
        val THREAD_FORMATTER by lazy { ThreadFormatter() }
        val STACK_TRACE_FORMATTER by lazy { StackTraceFormatter() }
    }

    fun getConfig(): SLogConfig = slicingConfig
    fun setConfig(sLogConfig: SLogConfig) {
        slicingConfig = sLogConfig
    }

    fun addPrinter(vararg printer: SLogPrinter) {
        slicingConfig.addPrinter(*printer)
    }

    internal fun getPrinterByTag(tag: String): SLogViewPrinter? {
        return slicingConfig.getPrinterByTag(tag)
    }

    fun removePrinter(printer: SLogPrinter) {
        slicingConfig.removePrinter(printer)
    }

    fun genLogFileName(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(Date(System.currentTimeMillis())) + ".txt"
    }

}