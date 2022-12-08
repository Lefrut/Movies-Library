package ru.dashkevich.profile.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dashkevich.profile.R
import ru.dashkevich.profile.databinding.FragmentSavedBinding


class SavedFragment : Fragment(R.layout.fragment_saved) {

    lateinit var binding: FragmentSavedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedBinding.bind(view)
    }

}