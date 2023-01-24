package com.example.mundialjoaquinfernandez.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.mundialjoaquinfernandez.R
import com.example.mundialjoaquinfernandez.databinding.ActivitySplashBinding
import com.example.mundialjoaquinfernandez.ui.pantallas.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            val animation =
                AnimationUtils.loadAnimation(this@SplashActivity, R.anim.splash_animation)
            mascota.startAnimation(animation)

            window.setFlags(
                android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
                android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }, 3000)
        }
    }
}