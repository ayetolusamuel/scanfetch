package com.codingwithset.sotech

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        initAnimationOnLogo()
        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }, 3000)
    }

    private fun initAnimationOnLogo() {
        ObjectAnimator.ofFloat(imageView, "alpha", 0.4f, 0.9f).apply {
            duration = 1000
            repeatCount = Animation.INFINITE
            start()
        }
    }
}