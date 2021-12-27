package org.eson.slog.printer

import org.eson.slog.SLogConfig

interface SLogPrinter {
    fun printer(config: SLogConfig,level: Int, tag: String, content: String)
}