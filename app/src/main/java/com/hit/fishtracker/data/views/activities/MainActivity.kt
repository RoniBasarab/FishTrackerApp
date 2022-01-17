package com.hit.fishtracker.data.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hit.fishtracker.R
import com.hit.fishtracker.data.database.FishDatabase

const val logger = "logger"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FishDatabase.invoke(applicationContext)
        setContentView(R.layout.activity_main)
    }
}