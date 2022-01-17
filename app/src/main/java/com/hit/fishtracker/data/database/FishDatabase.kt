package com.hit.fishtracker.data.database

import android.content.Context
import androidx.room.Room

object FishDatabase {
    @Volatile var db: Database? = null
    private val LOCK = Any()

    operator fun invoke(context: Context)= db ?: synchronized(LOCK){
        db ?: buildDatabase(context).also { db = it}
    }

    private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
        Database::class.java, "Fishes.db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}
