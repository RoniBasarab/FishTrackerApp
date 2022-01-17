package com.hit.fishtracker.data.views.fragments
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hit.fishtracker.BuildConfig
import com.hit.fishtracker.data.database.service.FishService
import com.hit.fishtracker.data.model.Fish
import com.hit.fishtracker.data.views.activities.logger
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainFragmentViewModel: ViewModel()
{

    val repository: MutableLiveData<MutableList<Fish>> by lazy {
        val livedata = MutableLiveData<MutableList<Fish>>()
        livedata.value = mutableListOf()
        livedata
    }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .build()
        .create(FishService::class.java)

    private suspend fun getFromAPI(fishName: String)
    {
        withContext(Dispatchers.IO)
        {
            try{
                if(fishName == "All")
                {
                    val body = retrofit.getAllFish()
                    repository.value?.plusAssign(body)
                    repository.postValue(repository.value)
                }
                else
                {
                    val body = retrofit.getFishByName(fishName)
                    repository.value?.plusAssign(body)
                    repository.postValue(repository.value)
                }
            }
            catch (e: Exception)
            {
                Log.d(logger, e.toString())
            }
        }
    }

    fun getFish(fishName: String)
    {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            getFromAPI(fishName)
        }
    }
}