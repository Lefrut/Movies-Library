package ru.dashkevich.viewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import ru.dashkevich.data.di.dataModules
import ru.dashkevich.domain.di.domainModules
import ru.dashkevich.viewapp.di.*

class StartActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController

        supportActionBar?.title = "Библиотека фильмов"

        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.basic_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
                    //android.R.id.home -> { onNavigateUp() }
                }
                return true
            }

            override fun onPrepareMenu(menu: Menu) {
                super.onPrepareMenu(menu)
                //val item = menu.findItem()
            }
        })


        startKoin {
            androidLogger()
            androidContext(this@StartActivity)
            modules(dataModules + domainModules + viewModelModule)
        }


    }

    fun updateToolbar(){
        when(val id = navController.currentDestination?.id){
            com.example.unknown.R.id.unknownFragment ->{
                supportActionBar?.hide()
            }else -> {
                //supportActionBar.
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("navController.popBackStack()"))
    override fun onBackPressed() {

        navController.popBackStack()
    }

    override fun onNavigateUp(): Boolean {
        stopKoin()
        return navController.navigateUp()
    }

}