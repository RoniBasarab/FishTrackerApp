package com.hit.fishtracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hit.fishtracker.data.model.Fish

@Database(entities = [Fish::class], version = 2)
abstract class Database : RoomDatabase(){
    abstract fun fishDao(): FishDao
}
