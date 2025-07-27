package com.lee.mymvvmsample.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.common.BaseActivity
import com.lee.mymvvmsample.presentation.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        showProgress()

        lifecycleScope.launch {
            delay(2000)
            hideProgress()
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
//        Handler().postDelayed({
//            hideProgress()
//            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
//            finish()
//        }, 2000)
    }
}
