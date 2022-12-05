package ru.dashkevich.viewapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module
import ru.dashkevich.viewapp.screens.login.LoginViewModel
import ru.dashkevich.viewapp.screens.main.library.LibraryViewModel
import ru.dashkevich.viewapp.screens.splash.SplashViewModel

val viewModelModule = module {

    viewModel { LibraryViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel{ SplashViewModel(get()) }

}