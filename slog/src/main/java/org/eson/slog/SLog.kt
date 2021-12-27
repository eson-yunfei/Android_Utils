package org.eson.slog

import android.text.TextUtils
import android.util.Log
import org.eson.slog.utils.getCroppedRealStackTrace

class SLog {

    companion object {

        private val packageName: String by lazy {
            val name = SLog::class.java.name
            name.substring(0, name.lastIndexOf(".") + 1)
        }

        fun v(message: String) {
            vv(getGlobalTag(), message)
        }

        fun d(message: String) {
            dd(getGlobalTag(), message)
        }

        fun i(message: String) {
            ii(getGlobalTag(), message)
        }

        fun w(message: String) {
            ww(getGlobalTag(), message)
        }

        fun e(message: String) {
            ee(getGlobalTag(), message)
        }

        fun vv(tag: String, message: String) {
            log(Log.VERBOSE, tag, message)
        }

        fun dd(tag: String, message: String) {
            log(Log.DEBUG, tag, message)
        }

        fun ii(tag: String, message: String) {
            log(Log.INFO, tag, message)
        }

        fun ww(tag: String, message: String) {
            log(Log.WARN, tag, message)
        }

        fun ee(tag: String, message: String) {
            log(Log.ERROR, tag, message)
        }

        private fun log(leave: Int, tag: String, message: String) {
            log(SLogManager.sLogManager.getConfig(), leave, tag, message)
        }

        fun log(config: SLogConfig, leave: Int, tag: String, message: String) {
            val sb = StringBuffer()
            if (config.isIncludeThread()) {
                val threadInfo: String = SLogManager.THREAD_FORMATTER.format(Thread.currentThread())
                sb.append(threadInfo).append("\n")
            }
            if (config.getStackTraceDepth() > 0) {
                if (sb.isEmpty()){
                    sb.append("\n")
                }
                val stackTrace = Throwable().stackTrace
                val croppedRealStackTrace = getCroppedRealStackTrace(
                    stackTrace, config.getStackTraceDepth(),
                    packageName
                )
                val stackTraceInfo: String =
                    SLogManager.STACK_TRACE_FORMATTER.format(croppedRealStackTrace)
                sb.append(stackTraceInfo)
                if (!TextUtils.isEmpty(stackTraceInfo)) {
                    sb.append("\n")
                }
            }
            sb.append(message)
            config.printerList.forEach {
                it.printer(config, leave, tag, sb.toString())
            }

        }


        private fun getGlobalTag(): String {
            return SLogManager.sLogManager.getConfig().getGlobalTag()
        }
    }


}