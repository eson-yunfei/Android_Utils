package org.eson.slog.utils

fun getCroppedRealStackTrace(
    stackTrace: Array<StackTraceElement>,
    maxDepth: Int,
    ignorePackage: String?
): Array<StackTraceElement?>? {
    val realStackTrace =
        getRealStackTrace(stackTrace, ignorePackage)
    return cropStackTrace(realStackTrace, maxDepth)
}

/**
 * 获取忽略包名之外的堆栈信息
 *
 * @param stackTrace
 * @param ignorePackage
 * @return
 */
private fun getRealStackTrace(
    stackTrace: Array<StackTraceElement>,
    ignorePackage: String?
): Array<StackTraceElement?> {
    var ignoreDepth = 0
    val allDepth = stackTrace.size
    var className: String
    for (i in allDepth - 1 downTo 0) {
        className = stackTrace[i].className
        if (ignorePackage != null && className.startsWith(ignorePackage)) {
            ignoreDepth = i + 1
            break
        }
    }
    val realDepth = allDepth - ignoreDepth
    val realStackTrace = arrayOfNulls<StackTraceElement>(realDepth)
    System.arraycopy(stackTrace, ignoreDepth, realStackTrace, 0, realDepth)
    return realStackTrace
}

/**
 * 裁剪 堆栈信息
 *
 * @param callStackTrace
 * @param maxDepth
 * @return
 */
private fun cropStackTrace(
    callStackTrace: Array<StackTraceElement?>,
    maxDepth: Int
): Array<StackTraceElement?> {
    var realDepth = callStackTrace.size
    if (maxDepth > 0) {
        realDepth = Math.min(realDepth, maxDepth)
    }
    val realStackTrace = arrayOfNulls<StackTraceElement>(realDepth)
    System.arraycopy(callStackTrace, 0, realStackTrace, 0, realDepth)
    return realStackTrace
}