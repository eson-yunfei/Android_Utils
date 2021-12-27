package org.eson.slog

import android.text.TextUtils
import org.eson.slog.printer.ConsolePrinter
import org.eson.slog.printer.SLogPrinter
import org.eson.slog.printer.file.SLogFilePrinter
import org.eson.slog.printer.view.SLogViewPrinter

class SLogConfig {
    val maxSize = 512
    val printerList: MutableList<SLogPrinter> = mutableListOf()

    private var includeThread = false
    private var stackTraceDepth = 3
    private var saveFile = false
    private var filePath = ""
    private var retentionTime: Long = 0L

    init {
        addPrinter(ConsolePrinter())
    }

    fun config(fuc: SLogConfig.() -> Unit): SLogConfig {
        this.fuc()
        return this
    }


    fun setIncludeThread(include: Boolean) {
        includeThread = include
    }

    fun setStackTraceDepth(depth: Int) {
        stackTraceDepth = depth
    }

    fun setSaveFile(
        enable: Boolean,
        savePath: String,
        retentionTime: Long = 5 * 24 * 60 * 60 * 1000
    ) {
        saveFile = enable
        filePath = savePath
        this.retentionTime = retentionTime
        addPrinter(SLogFilePrinter.filePrinter)
    }


    internal fun getGlobalTag(): String  = "SLog"

    internal fun isIncludeThread(): Boolean = includeThread

    internal fun getStackTraceDepth(): Int = stackTraceDepth
    internal fun getSavePath(): String = filePath
    internal fun getRetentionTime(): Long  = retentionTime
    fun addPrinter(vararg printers: SLogPrinter) {

        printers.forEach {
            if (!printerList.contains(it)) {
                printerList.add(it)
            }
        }
    }

    internal fun getPrinterByTag(tag: String): SLogViewPrinter? {
        var sLogViewPrinter: SLogViewPrinter? = null
        printerList.forEach {
            if (it is SLogViewPrinter) {
                if (TextUtils.equals(tag, it.tag)) {
                    sLogViewPrinter = it
                }
            }
        }
        return sLogViewPrinter
    }

    internal fun removePrinter(printer: SLogPrinter) {
        printerList.remove(printer)
    }




}