package ru.dashkevich.viewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.dashkevich.viewapp.di.*

class StartActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController

        startKoin {
            androidLogger()
            androidContext(this@StartActivity)
            modules(appModules + repositoryModule + viewModelModule)
        }


    }

    @Deprecated("Deprecated in Java", ReplaceWith("navController.popBackStack()"))
    override fun onBackPressed() {
        navController.popBackStack()
    }

}