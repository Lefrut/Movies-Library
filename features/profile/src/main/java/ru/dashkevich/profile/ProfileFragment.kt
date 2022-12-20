package ru.dashkevich.profile

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import ru.dashkevich.profile.adapter.ProfileTabsAdapter
import ru.dashkevich.profile.databinding.FragmentProfileBinding
import ru.dashkevich.profile.dialog.ExitDialogFragment
import ru.dashkevich.profile.tabs.saved.SavedFragment
import ru.dashkevich.profile.tabs.settings.SettingsFragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        val tabs = listOf(Pair(SavedFragment(), "Сохранено"), Pair(SettingsFragment(), "Настройки"))


        setHasOptionsMenu(true)

        //No work
//        val menuHost: MenuHost = requireActivity()
//
//        menuHost.addCustomMenu()

        val profileTabsAdapter = ProfileTabsAdapter(this, tabs)
        binding.pager.adapter = profileTabsAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { itemTab, position ->
            itemTab.text = tabs[position].second
        }.attach()
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_m, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_item_profile -> {
                ExitDialogFragment()
                    .show(childFragmentManager, ExitDialogFragment.TAG)
            }
        }
        return true
    }


    //No work
    private fun MenuHost.addCustomMenu() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Профиль"
        }
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.profile_m, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.exit_item_profile -> {
                        ExitDialogFragment()
                            .show(childFragmentManager, ExitDialogFragment.TAG)
                        //findNavController().navigate(R.id.action)
                        Log.d("Menu", "Menu exit item clicked")
                        return onMenuItemSelected(menuItem)
                    }
                }
                Log.d("Menu", "onMenuItemSelected worked")

                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Библиотека фильмов"
        }
    }

}