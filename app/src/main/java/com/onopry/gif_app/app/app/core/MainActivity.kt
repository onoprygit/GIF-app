package com.onopry.gif_app.app.app.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onopry.gif_app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}