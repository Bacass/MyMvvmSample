package com.lee.mymvvmsample.common.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.window.layout.WindowMetricsCalculator

class DeviceInfo {
    companion object {
        fun appVersionInfo(context: Context): String {
            return try {
                val pm: PackageManager = context.packageManager
                val pkg = context.packageName
                val versionName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0)).versionName
                } else {
                    @Suppress("DEPRECATION")
                    pm.getPackageInfo(pkg, 0).versionName
                }
                "v $versionName"
            } catch (_: Exception) {
                "v"
            }
        }

        fun getDeviceWidth(context: Context): Int {
            return try {
                val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context as android.app.Activity)
                windowMetrics.bounds.width()
            } catch (_: Exception) {
                val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val metrics = DisplayMetrics()
                @Suppress("DEPRECATION")
                wm.defaultDisplay.getMetrics(metrics)
                metrics.widthPixels
            }
        }
    }
}
