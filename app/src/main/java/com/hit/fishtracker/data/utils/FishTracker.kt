package com.hit.fishtracker.data.utils
import android.app.Application
import com.hit.fishtracker.data.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FishTracker : Application()
{
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FishTracker)
            modules(listOf(appModule))
        }

    }
}