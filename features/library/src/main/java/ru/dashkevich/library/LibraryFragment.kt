package ru.dashkevich.library

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
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
    private val pagingFilmsAdapter by lazy { initPagingFilmAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLibraryBinding.bind(view)

        
        val menuHost = requireActivity() as? MenuHost
        menuHost?.addMenuProvider( object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.library_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        })


        binding.recyclerView.apply {
            adapter = pagingFilmsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.viewState.observe(viewLifecycleOwner) { libraryValue ->
            libraryValue.apply {
                when (screenStatus) {
                    ScreenStatus.Success -> {
                        binding.loadingBar.isVisible = false
                        binding.linearLayout.isVisible = true
                    }
                    ScreenStatus.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Error loading data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    ScreenStatus.Loading -> {
                        binding.loadingBar.isVisible = true
                        binding.linearLayout.isVisible = false
                    }
                    ScreenStatus.EmptyResult -> {

                    }
                    ScreenStatus.Waiting -> {
                        binding.loadingBar.isVisible = true
                        binding.linearLayout.isVisible = false
                    }
                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.noFilmsFlow.collectLatest { value: PagingData<PresentedFilm> ->
                    launch {
                        pagingFilmsAdapter.submitData(value)
                    }
                    delay(400)
                    viewModel.screenStatusUpdate(ScreenStatus.Success)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.processingEvent(LibraryEvent.LeavingScreen)
    }

    private fun initPagingFilmAdapter(): PagingFilmsAdapter {
        return PagingFilmsAdapter(
            diffCallback = FilmsComparator,
            onSavedClick = { saved, index ->
                viewModel.processingEvent(
                    LibraryEvent.SavedFilmClicked(saved, index)
                )
            }
        )
    }

}