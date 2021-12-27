package org.eson.slog.printer.file

import org.eson.slog.SLogBean
import java.io.File
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

internal class PrintWork(private val writer: LogWriter) : Runnable {

    private val logs: BlockingQueue<SLogBean> = LinkedBlockingQueue()

    @Volatile
    private var running = false

    /**
     * 将log放入打印队列
     *
     * @param log 要被打印的log
     */
    fun put(log: SLogBean) {
        kotlin.runCatching { logs.put(log) }
    }

    /**
     * 判断工作线程是否还在运行中
     *
     * @return true 在运行
     */
    fun isRunning(): Boolean {
        synchronized(this) { return running }
    }

    fun startWork() {
        synchronized(this) {
            SLogFilePrinter.EXECUTOR.execute(this)
            running = true
        }
    }

    override fun run() {
        var log: SLogBean?
        kotlin.runCatching {
            while (true) {
                log = logs.take()
                writer.writerLog(log!!)
            }
        }.onFailure {
            synchronized(this) { running = false }
        }
    }

}