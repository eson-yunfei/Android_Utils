package org.eson.slog.printer.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.eson.slog.R

class LogViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    val tagView: TextView = item.findViewById(R.id.text_tag)
    val messageView: TextView = item.findViewById(R.id.text_message)
}