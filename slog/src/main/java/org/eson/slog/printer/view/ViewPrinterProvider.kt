package org.eson.slog.printer.view

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.eson.slog.utils.dp2px

const val TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW"
const val TAG_LOG_VIEW = "TAG_LOG_VIEW"

class ViewPrinterProvider(private val rootView: FrameLayout,  private val recyclerView: RecyclerView) {

    private var floatingView: View? = null
    private var logView: FrameLayout? = null
    private var isOpen = false
    fun showFloatingView() {
        if (rootView.findViewWithTag<View>(TAG_FLOATING_VIEW) != null) {
            return
        }
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.BOTTOM or Gravity.END
        val floatingView = genFloatingView()
        floatingView.tag = TAG_FLOATING_VIEW
        floatingView.setBackgroundColor(Color.BLACK)
        floatingView.alpha = 0.8f
        params.bottomMargin = dp2px(100f)
        rootView.addView(floatingView, params)
    }

    fun closeFloatingView() {
        rootView.removeView(genFloatingView())
    }

    private fun genFloatingView(): View {
        if (floatingView != null) {
            return floatingView!!
        }
        val textView = TextView(rootView.context)
        textView.text = "SLog"
        textView.setOnClickListener {
            if (!isOpen) {
                showLogView()
            }
        }
        return textView.also { floatingView = it }
    }

    private fun showLogView() {
        if (rootView.findViewWithTag<View>(TAG_LOG_VIEW) != null) {
            return
        }
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            dp2px(160f)
        )
        params.gravity = Gravity.BOTTOM
        val floatingView = genLogView()
        floatingView.tag = TAG_LOG_VIEW
        rootView.addView(floatingView, params)
        isOpen = true
    }

    private fun genLogView(): View {
        if (logView != null) {
            return logView!!
        }
        val logViewContainer = FrameLayout(rootView.context)
        logViewContainer.setBackgroundColor(Color.BLACK)
        logViewContainer.addView(recyclerView)
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.END
        val closeView = TextView(rootView.context)
        closeView.text = "Close"
        closeView.setOnClickListener { closeLogView() }
        logViewContainer.addView(closeView, params)
        return logViewContainer.also { logView = it }
    }

    private fun closeLogView() {
        isOpen = false
        rootView.removeView(genLogView())
    }
}