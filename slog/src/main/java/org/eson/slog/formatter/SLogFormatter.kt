package org.eson.slog.formatter

interface SLogFormatter<T> {
    fun format(data: T): String
}