package ru.dashkevich.profile.tabs.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import ru.dashkevich.profile.R
import ru.dashkevich.profile.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

    }

}