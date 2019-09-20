package com.lee.mymvvmsample.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.ui.main.MainActivity

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Handler().postDelayed({
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }
}
