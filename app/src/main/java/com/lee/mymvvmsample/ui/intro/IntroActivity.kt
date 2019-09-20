package com.lee.mymvvmsample.ui.intro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.common.BaseActivity
import com.lee.mymvvmsample.ui.main.MainActivity

class IntroActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        showProgress()

        Handler().postDelayed({
            hideProgress()
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }
}
