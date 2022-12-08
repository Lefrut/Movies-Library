package ru.dashkevich.viewapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.dashkevich.authorization.screens.login.LoginViewModel
import ru.dashkevich.authorization.screens.splash.SplashViewModel
import ru.dashkevich.library.LibraryViewModel

val viewModelModule = module {

    viewModel { LibraryViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SplashViewModel(get()) }

}