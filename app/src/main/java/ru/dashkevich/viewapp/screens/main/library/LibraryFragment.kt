package ru.dashkevich.viewapp.screens.main.library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.data.api.movies.util.moviesApi
import ru.dashkevich.viewapp.data.repository.MoviesRepository
import ru.dashkevich.viewapp.databinding.FragmentLibraryBinding
import ru.dashkevich.viewapp.screens.login.LoginViewModel
import ru.dashkevich.viewapp.screens.main.library.adapter.MoviesAdapter
import ru.dashkevich.viewapp.screens.main.library.model.LibraryEvent

class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var binding: FragmentLibraryBinding
    private val viewModel: LibraryViewModel by viewModels {
        LibraryViewModel.Companion.Factory(MoviesRepository(moviesApi = moviesApi))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLibraryBinding.bind(view)

        val moviesAdapter = MoviesAdapter()

        binding.recyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.libraryState.observe(viewLifecycleOwner) { libraryValue ->
            moviesAdapter.setData(libraryValue.movies.films)
        }

        binding.requestResult.setOnClickListener {
            viewModel.processingEvent(LibraryEvent.RequestResultClicked)
        }

    }

}