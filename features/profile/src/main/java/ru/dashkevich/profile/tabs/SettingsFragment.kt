package ru.dashkevich.profile.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dashkevich.profile.R
import ru.dashkevich.profile.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

    }

}