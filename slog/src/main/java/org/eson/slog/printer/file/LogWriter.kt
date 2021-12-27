package org.eson.slog.printer.file

import org.eson.slog.SLogBean
import org.eson.slog.SLogManager
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

internal class LogWriter(private val savePath: String) {
    private var preFileName: String? = null
    private var logFile: File? = null
    private var bufferedWriter: BufferedWriter? = null

    fun writerLog(sLogBean: SLogBean) {
        preFileName ?: kotlin.run {
            val fileName = SLogManager.sLogManager.genLogFileName()
            closeBuffer()
            if (!createLogFile(fileName)) {
                return
            }
        }
        write(sLogBean.flattenedLog())
    }

    /**
     * 将log写入文件
     *
     * @param flattenedLog 格式化后的log
     */
    private fun write(flattenedLog: String) {
        try {
            bufferedWriter!!.write(flattenedLog)
            bufferedWriter!!.newLine()
            bufferedWriter!!.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun createLogFile(fileName: String): Boolean {
        preFileName = fileName
        logFile = File(savePath, fileName)
        logFile ?: return false
        // 当log文件不存在时创建log文件
        if (!logFile!!.exists()) {
            kotlin.runCatching {
                val parent: File? = logFile!!.parentFile
                if (!parent!!.exists()) {
                    parent.mkdirs()
                }
                logFile!!.createNewFile()
            }.onSuccess {

            }.onFailure {
                preFileName = null
                logFile = null
                return false
            }
        }

        try {
            bufferedWriter = BufferedWriter(FileWriter(logFile, true))
        } catch (e: Exception) {
            e.printStackTrace()
            preFileName = null
            logFile = null
            return false
        }
        return true
    }


    private fun closeBuffer() {
        kotlin.runCatching {
            bufferedWriter?.close()
            bufferedWriter?.flush()
            bufferedWriter = null
            preFileName = null
            logFile = null
        }
    }



}