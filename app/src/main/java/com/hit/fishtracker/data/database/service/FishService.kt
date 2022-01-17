package com.hit.fishtracker.data.database.service
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hit.fishtracker.data.model.Fish
import retrofit2.http.GET
import retrofit2.http.Query

interface FishService
{
    @GET("fish")
    suspend fun getAllFish(): List<Fish>

    @GET("fish")
    suspend fun getFishByName(@Query("search") search: String): List<Fish>


}