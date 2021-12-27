package org.eson.slog.printer.view

import android.app.Activity
import android.util.Log
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.eson.slog.SLogBean
import org.eson.slog.SLogConfig
import org.eson.slog.printer.SLogPrinter
import java.lang.StringBuilder

class SLogViewPrinter constructor(activity: Activity, val tag: String) : SLogPrinter {

    private val viewAdapter: SLogViewAdapter = SLogViewAdapter(activity.layoutInflater)
    private val rootView: FrameLayout = activity.findViewById(android.R.id.content)
    private val recyclerView: RecyclerView = RecyclerView(activity)
    private val linearLayoutManager = LinearLayoutManager(activity)
    private var viewPrinterProvider: ViewPrinterProvider

    init {
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = viewAdapter
        viewPrinterProvider = ViewPrinterProvider(rootView, recyclerView)
    }

    fun getViewPrinterProvider(): ViewPrinterProvider {
        return viewPrinterProvider
    }

    override fun printer(config: SLogConfig, level: Int, tag: String, content: String) {

        val length = content.length
        val countOfSub: Int = length / config.maxSize
        if (countOfSub <= 0) {
            printLog(level, tag, content)
            return
        }
        val stringBuilder = StringBuilder()
        var index = 0
        for (i in 0 until countOfSub) {
            val substring = content.substring(index, index + config.maxSize)
            stringBuilder.append(substring).append("\n")
            index += config.maxSize
        }
        if (index != length) {
            val substring = content.substring(index, length)
            stringBuilder.append(substring)
        }
        printLog(level, tag, stringBuilder.toString())
    }
    private fun printLog(level: Int, tag: String, content: String){
        val sLogBean = SLogBean(level, tag, content)
        viewAdapter.addLog(sLogBean)
        recyclerView.scrollToPosition(viewAdapter.itemCount - 1)
    }
}