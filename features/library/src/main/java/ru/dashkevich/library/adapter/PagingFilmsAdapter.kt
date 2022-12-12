package ru.dashkevich.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.library.R
import ru.dashkevich.library.adapter.PagingFilmsAdapter.PagingFilmsViewHolder

class PagingFilmsAdapter(
    diffCallback: DiffUtil.ItemCallback<PresentedFilm>,
    private val onSavedClick: (Boolean, Int) -> Unit = { _, _ -> }
) : PagingDataAdapter<PresentedFilm, PagingFilmsViewHolder>(diffCallback) {


    class PagingFilmsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vImage: ImageView = view.findViewById(R.id.film_poster)
        val vTitle: TextView = view.findViewById(R.id.film_title)
        val vRating: TextView = view.findViewById(R.id.film_rating)
        val vSaved: ImageView = view.findViewById(R.id.film_saved)
    }

    override fun onBindViewHolder(holder: PagingFilmsViewHolder, position: Int) {
        val film = getItem(position) ?: return
        holder.apply {
            film.apply {
                if (saved) vSaved.setImageResource(R.drawable.ic_filled_bookmark)
                else vSaved.setImageResource(R.drawable.ic_bookmark)
                vSaved.setOnClickListener { onSavedClick(!saved, position) }
                vTitle.text = title
                vRating.text = rating
                vImage.load(posterUrl) {
                    scale(Scale.FIT)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PagingFilmsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return PagingFilmsViewHolder(view)
    }
}