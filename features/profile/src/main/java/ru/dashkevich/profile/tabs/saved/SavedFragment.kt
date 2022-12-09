package ru.dashkevich.profile.tabs.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.android.ext.android.inject
import ru.dashkevich.library.adapter.MoviesAdapter
import ru.dashkevich.profile.R
import ru.dashkevich.profile.databinding.FragmentSavedBinding
import ru.dashkevich.profile.tabs.saved.presenter.SavedPresenter


class SavedFragment : Fragment(R.layout.fragment_saved) {

    private lateinit var binding: FragmentSavedBinding
    private val presenter: SavedPresenter by inject()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedBinding.bind(view)
        moviesAdapter = MoviesAdapter(
            onSavedClick = { saved, index ->
                presenter.updateDeleteUI(saved,index, moviesAdapter)
            }
        )
        binding.savedRecyclerView.adapter = moviesAdapter
        binding.savedRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        presenter.updateGetUI(adapter = moviesAdapter)
    }

}