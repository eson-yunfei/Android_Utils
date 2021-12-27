package org.eson.slog.printer.view

import android.app.Activity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import org.eson.slog.SLog
import org.eson.slog.SLogConfig
import org.eson.slog.SLogManager

interface IViewPrinter<T : Activity> : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        SLog.d("IViewPrinter onCreate")
        val activity = getActivity()
        val viewPrinter = SLogViewPrinter(activity,activity.javaClass.name)
        SLog.d("viewPrinter  $viewPrinter")
        viewPrinter.getViewPrinterProvider().showFloatingView()
        SLogManager.sLogManager.addPrinter(viewPrinter)
    }


    override fun onDestroy(owner: LifecycleOwner) {

        val activity = getActivity()
        SLogManager.sLogManager.getPrinterByTag(activity.javaClass.name)?.let {
            it.getViewPrinterProvider().closeFloatingView()
            SLogManager.sLogManager.removePrinter(it)
        }

    }

    fun getActivity():T
}