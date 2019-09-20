package com.lee.mymvvmsample.common.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.os.Environment
import com.lee.mymvvmsample.common.MyApplication
import java.io.File
import java.io.FileWriter
import java.io.IOException


class DeviceInfo {

    companion object {
        fun appVersionInfo(context: Context): String {
            var pi: PackageInfo? = null
            try {
                pi = context.packageManager.getPackageInfo(context.packageName, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "v " + pi?.versionName!!
        }

        fun writeStringToFile(fileContents: String, fileName: String) {
            try {
                var sPackage = MyApplication.mContext?.packageName
                // to this path add a new directory path
                var sPath =
                    Environment.getExternalStorageDirectory().absolutePath + "/Android/data/" + sPackage + "/logs/"

                var dir = File(sPath)
                // create this directory if not already created
                if (!dir.exists())
                    dir.mkdirs()

                var out = FileWriter(File(sPath, fileName))
                out.write(fileContents)
                out.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}