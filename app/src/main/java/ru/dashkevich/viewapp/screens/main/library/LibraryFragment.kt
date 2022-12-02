package ru.dashkevich.viewapp.screens.main.library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.databinding.FragmentLibraryBinding

class LibraryFragment: Fragment(R.layout.fragment_library) {

    private lateinit var binding: FragmentLibraryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLibraryBinding.bind(view)


    }

}