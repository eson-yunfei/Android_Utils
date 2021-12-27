package org.eson.slog.utils

import android.content.res.Resources
import android.util.TypedValue


fun dp2px(dp: Float): Int {
    val displayMetrics = Resources.getSystem().displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
        .toInt()
}