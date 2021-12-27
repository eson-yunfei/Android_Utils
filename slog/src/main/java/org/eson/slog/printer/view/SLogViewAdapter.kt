package org.eson.slog.printer.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.eson.slog.R
import org.eson.slog.SLogBean

internal class SLogViewAdapter(private val layoutInflater: LayoutInflater): RecyclerView.Adapter<LogViewHolder>() {
    private val list:MutableList<SLogBean> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val inflate = layoutInflater.inflate(R.layout.s_log_view_item, parent, false)
        return LogViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val sLogBean = list[position]
        val highLightColor = getHighLightColor(sLogBean.leave)

        holder.tagView.setTextColor(highLightColor)
        holder.messageView.setTextColor(highLightColor)

        Log.d("lll",sLogBean.log)

        holder.tagView.text = sLogBean.getFlattened()
        holder.messageView.text = sLogBean.log
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addLog(sLogBean: SLogBean) {
        list.add(sLogBean)
        notifyItemInserted(list.size-1)
    }

    private fun getHighLightColor(level: Int): Int {
        val highLight: Int = when (level) {
            Log.VERBOSE -> -0x444445
            Log.DEBUG -> -0x1
            Log.INFO -> -0x9578a7
            Log.WARN -> -0x444ad7
            Log.ERROR -> -0x449498
            else -> -0x100
        }
        return highLight
    }
}