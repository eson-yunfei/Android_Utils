package org.eson.slog

fun logV(message: String) {
    SLog.v(message)
}

fun logD(message: String) {
    SLog.d(message)
}

fun logI(message: String) {
    SLog.i(message)
}

fun logW(message: String) {
    SLog.w(message)
}

fun logE(message: String) {
    SLog.e(message)
}

fun log2V(tag: String, message: String) {
   SLog.vv(tag, message)
}

fun log2d(tag: String, message: String) {
    SLog.dd(tag, message)
}

fun log2i(tag: String, message: String) {
    SLog.ii(tag, message)
}

fun log2w(tag: String, message: String) {
    SLog.ww(tag, message)
}

fun log2e(tag: String, message: String) {
    SLog.ee(tag, message)
}

inline fun <reified T> T.logTv(message: String) {
    log2V(T::class.java.simpleName,message)
}

inline fun <reified T> T.logTd(message: String) {
    log2d(T::class.java.simpleName,message)
}
inline fun <reified T> T.logTi(message: String) {
    log2i(T::class.java.simpleName,message)
}
inline fun <reified T> T.logTw(message: String) {
    log2w(T::class.java.simpleName,message)
}
inline fun <reified T> T.logTe(message: String) {
    log2e(T::class.java.simpleName,message)
}
