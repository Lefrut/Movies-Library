package ru.dashkevich.navigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.dashkevich.navigation.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var binding: FragmentMainBinding
    private lateinit var tabsNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)


        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.main_fragment_container) as NavHostFragment
        tabsNavController = navHostFragment.navController


        binding.bottomNav.setupWithNavController(tabsNavController)
    }







}