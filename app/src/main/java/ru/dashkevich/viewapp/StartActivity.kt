package ru.dashkevich.viewapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.dashkevich.viewapp.databinding.ActivityStartBinding
import ru.dashkevich.viewapp.util.log.logE

class StartActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController


    }

    @Deprecated("Deprecated in Java", ReplaceWith("navController.popBackStack()"))
    override fun onBackPressed() {
        navController.popBackStack()
    }

}