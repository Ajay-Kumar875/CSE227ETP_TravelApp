package com.example.cse227etp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    lateinit var appname:TextView
    lateinit var lottie : LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appname=findViewById(R.id.appName)
        lottie = findViewById(R.id.lottie)

        appname.animate().translationY(-1900F).setDuration(2700).setStartDelay(0)
        lottie.animate().translationX(2000F).setDuration(2000).setStartDelay(2900)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val  i= Intent(this,LoginPage::class.java)
                startActivity(i)
                finish()
            },5000)

    }
}