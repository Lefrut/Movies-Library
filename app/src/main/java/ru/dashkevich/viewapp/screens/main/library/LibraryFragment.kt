package ru.dashkevich.viewapp.screens.main.library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.data.api.movies.util.moviesApi
import ru.dashkevich.viewapp.data.repository.MoviesRepository
import ru.dashkevich.viewapp.databinding.FragmentLibraryBinding
import ru.dashkevich.viewapp.screens.main.library.adapter.MoviesAdapter
import ru.dashkevich.viewapp.screens.main.library.model.LibraryEvent
import ru.dashkevich.viewapp.screens.main.library.model.ScreenStatus
import ru.dashkevich.viewapp.util.ui.toast

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
            layoutManager = GridLayoutManager(requireContext(),2)
        }

        viewModel.viewState.observe(viewLifecycleOwner) { libraryValue ->
            libraryValue.apply {
                when (screenStatus) {
                    ScreenStatus.Success -> {
                        moviesAdapter.setData(movies.films)
                        toast("Success", requireContext())
                    }
                    ScreenStatus.Error -> {

                    }
                    ScreenStatus.Loading -> {

                    }
                    ScreenStatus.Waiting -> {

                    }
                }

            }
        }

        binding.requestResult.setOnClickListener {
            viewModel.processingEvent(LibraryEvent.RequestResultClicked)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.processingEvent(LibraryEvent.LeavingScreen)
    }

}