package com.aj.pickandpick.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lee.mymvvmsample.R

/**
 * 좌우 스크롤 Animation BaseActivity
 *
 * @author hjhan
 * @since 2019.09.04
 */
abstract class BaseContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        finishActivity()
    }

    override fun onPause() {
        super.onPause()
    }

    fun finishActivity() {
        finish()
        overridePendingTransition(R.anim.slide_none, R.anim.slide_out_right)
    }
}