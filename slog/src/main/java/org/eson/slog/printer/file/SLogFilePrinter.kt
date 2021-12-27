package org.eson.slog.printer.file

import org.eson.slog.SLogBean
import org.eson.slog.SLogConfig
import org.eson.slog.printer.SLogPrinter
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

internal class SLogFilePrinter : SLogPrinter {
    companion object {
        val EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()
        val filePrinter by lazy {
            SLogFilePrinter()
        }
    }

    private var logWriter: LogWriter? = null
    private var printWork: PrintWork? = null
    override fun printer(config: SLogConfig, level: Int, tag: String, content: String) {
        synchronized(this) {
            if (logWriter == null) {
                cleanExpiredLog(config.getSavePath(), config.getRetentionTime())
                logWriter = LogWriter(config.getSavePath())
                printWork = PrintWork(logWriter!!)
            }
        }
        printWork?.let {
            if (!it.isRunning()) {
                it.startWork()
            }
            val sLogBean = SLogBean(level, tag, content)
            it.put(sLogBean)
        }
    }

    private fun cleanExpiredLog(savePath: String, retentionTime: Long) {
        if (retentionTime <= 0) {
            return
        }
        val currentTimeMillis = System.currentTimeMillis()
        val logDir = File(savePath)
        val files = logDir.listFiles() ?: return
        for (file in files) {
            if (currentTimeMillis - file.lastModified() > retentionTime) {
                file.delete()
            }
        }
    }

}