package com.hit.fishtracker.data.database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hit.fishtracker.data.model.Fish


@Dao
interface FishDao
{
    @Query("SELECT * FROM `Fish Table`")
    fun getAllFish(): MutableList<Fish>

    @Query("SELECT * from `Fish Table` WHERE Name LIKE '%' || :fishName || '%'")
    fun getFishByName(fishName: String): Fish

    @Insert
    fun addFish(fish: Fish)

    @Insert
    fun addAllFish(fishList: MutableList<Fish>)

    @Delete
    fun deleteFish(fish: Fish)

}