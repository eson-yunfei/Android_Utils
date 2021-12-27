package org.eson.slog.formatter

class ThreadFormatter :SLogFormatter<Thread> {
    override fun format(data: Thread): String {
        return "Thread: " + data.name
    }
}