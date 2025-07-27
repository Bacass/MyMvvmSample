package com.lee.mymvvmsample.common

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.lee.mymvvmsample.R

/**
 * 좌우 스크롤 Animation BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {
    private var progressDialog: AppCompatDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

//    override fun onBackPressed() {
//        finishActivity()
//    }

    override fun onPause() {
        super.onPause()
    }

    fun finishActivity() {
        finish()
        overridePendingTransition(R.anim.slide_none, R.anim.slide_out_right)
    }

    fun showProgress() {
        try {
            hideProgress()
            progressDialog = AppCompatDialog(this, R.style.ProgressDialogTheme)
            progressDialog?.let {
                it.setContentView(R.layout.common_progress)
                it.setCancelable(false)
                it.setCanceledOnTouchOutside(false)
                it.window?.setDimAmount(0.0f)
                it.show()
            }
        } catch (e: Exception) {
        }
    }

    fun hideProgress() {
        progressDialog?.let {
            try {
                it.dismiss()
            } catch (e: Exception) {
            }
            progressDialog = null
        }
    }

    fun hideKeyboard(v: View) {
        var imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
