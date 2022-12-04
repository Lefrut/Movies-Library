package ru.dashkevich.viewapp.screens.main.library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.data.api.movies.util.moviesApi
import ru.dashkevich.viewapp.data.repository.MoviesRepository
import ru.dashkevich.viewapp.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var binding: FragmentLibraryBinding
    private lateinit var viewModel: LibraryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            LibraryViewModel.Companion.Factory(
                MoviesRepository(moviesApi = moviesApi)
            )
        )[LibraryViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLibraryBinding.bind(view)

        viewModel.libraryState.observe(viewLifecycleOwner) {

        }

    }

}