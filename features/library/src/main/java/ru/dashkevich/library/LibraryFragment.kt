package ru.dashkevich.library

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.library.adapter.FilmsComparator
import ru.dashkevich.library.adapter.PagingFilmsAdapter
import ru.dashkevich.library.databinding.FragmentLibraryBinding
import ru.dashkevich.library.model.mvi.LibraryEvent
import ru.dashkevich.library.model.mvi.ScreenStatus

class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var binding: FragmentLibraryBinding
    private val viewModel by viewModel<LibraryViewModel>()
    private val pagingFilmsAdapter by lazy {
        PagingFilmsAdapter(
            diffCallback = FilmsComparator,
            onSavedClick = { saved, index ->
                viewModel.processingEvent(
                    LibraryEvent.SavedFilmClicked(saved, index)
                )
            }
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLibraryBinding.bind(view)


//        val moviesAdapter = MoviesAdapter(
//            onSavedClick = { saved, index ->
//                viewModel.processingEvent(LibraryEvent.SavedFilmClicked(saved, index))
//            }
//        )


        binding.recyclerView.apply {
//            adapter = moviesAdapter
//            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = pagingFilmsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.viewState.observe(viewLifecycleOwner) { libraryValue ->
            libraryValue.apply {
                when (screenStatus) {
                    ScreenStatus.Success -> {
//                        moviesAdapter.setData(movies.films)
                        CoroutineScope(Dispatchers.Main).launch {
                            pagingFilmsAdapter.submitData(pagingData = pagingMoviesData)
                        }
                    }
                    ScreenStatus.Error -> {

                    }
                    ScreenStatus.Loading -> {

                    }
                    ScreenStatus.Waiting -> {

                    }
                    ScreenStatus.EmptyResult -> {

                    }
                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.noFilmsFlow.collectLatest { value: PagingData<PresentedFilm> ->
                pagingFilmsAdapter.submitData(value)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.processingEvent(LibraryEvent.LeavingScreen)
    }

}