package com.example.login.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.Theme_Login)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}