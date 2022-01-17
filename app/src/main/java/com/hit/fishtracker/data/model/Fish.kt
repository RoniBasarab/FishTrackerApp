package com.hit.fishtracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Fish Table")
data class Fish(
    @PrimaryKey(autoGenerate = true) val innerID: Int,
    @ColumnInfo(name="id") val id: Int,
    @ColumnInfo(name="Name") val name: String,
    @ColumnInfo(name="Maximum Fish Age") val maxAge: Int?,
    @ColumnInfo(name="Maximum Fish Length") val maxLength: Double,
    @ColumnInfo(name="Maximum Fish Weight") val maxWeight: Double?,
    @ColumnInfo(name="Lives In Environment") val environment: String,
    @ColumnInfo(name="Fact") val biology: String,
)
