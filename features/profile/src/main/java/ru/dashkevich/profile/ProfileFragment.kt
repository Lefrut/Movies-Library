package ru.dashkevich.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.dashkevich.profile.adapter.ProfileTabsAdapter
import ru.dashkevich.profile.databinding.FragmentProfileBinding
import ru.dashkevich.profile.tabs.saved.SavedFragment
import ru.dashkevich.profile.tabs.settings.SettingsFragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        val tabs = listOf(Pair(SavedFragment(), "Сохранено"), Pair(SettingsFragment(), "Настройки"))

        val profileTabsAdapter = ProfileTabsAdapter(this, tabs)
        binding.pager.adapter = profileTabsAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager){ itemTab, position ->
            itemTab.text = tabs[position].second
        }.attach()
    }

}