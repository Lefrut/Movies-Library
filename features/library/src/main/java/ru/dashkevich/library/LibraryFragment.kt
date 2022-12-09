package ru.dashkevich.library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dashkevich.library.adapter.MoviesAdapter
import ru.dashkevich.library.databinding.FragmentLibraryBinding
import ru.dashkevich.library.model.mvi.LibraryEvent
import ru.dashkevich.library.model.mvi.ScreenStatus
import ru.dashkevich.utility.ui.toast

class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var binding: FragmentLibraryBinding
    private val viewModel by viewModel<LibraryViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLibraryBinding.bind(view)

        val moviesAdapter = MoviesAdapter(
            onSavedClick = { saved, index ->
                viewModel.processingEvent(LibraryEvent.SavedFilmClicked(saved, index))

            }
        )


        binding.recyclerView.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.viewState.observe(viewLifecycleOwner) { libraryValue ->
            libraryValue.apply {
                when (screenStatus) {
                    ScreenStatus.Success -> {
                        moviesAdapter.setData(movies.films)
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