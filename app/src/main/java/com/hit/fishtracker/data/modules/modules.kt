package com.hit.fishtracker.data.modules
import com.hit.fishtracker.data.views.fragments.MainFragmentViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
   viewModel { MainFragmentViewModel() }
}