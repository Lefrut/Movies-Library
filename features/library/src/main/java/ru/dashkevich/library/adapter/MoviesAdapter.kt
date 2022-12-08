package ru.dashkevich.library.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.dashkevich.data.api.model.Film
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.library.R
import ru.dashkevich.library.model.mvi.LibraryEvent

class MoviesAdapter(
    private var films: List<PresentedFilm> = emptyList(),
    val onSavedClick: (Boolean, Int) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vImage: ImageView = view.findViewById(R.id.film_poster)
        val vTitle: TextView = view.findViewById(R.id.film_title)
        val vRating: TextView = view.findViewById(R.id.film_rating)
        val vSaved: ImageView = view.findViewById(R.id.film_saved)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val film = films[position]
        holder.apply {
            film.apply {
                if (saved) vSaved.setImageResource(R.drawable.ic_filled_bookmark)
                else vSaved.setImageResource(R.drawable.ic_bookmark)
                vSaved.setOnClickListener{ onSavedClick(!saved, position) }

                vTitle.text = title
                vRating.text = rating
                vImage.load(posterUrl) {
                    scale(Scale.FIT)
                }
            }
        }
    }

    override fun getItemCount(): Int = films.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(films: List<PresentedFilm>) {
        this.films = films
        notifyDataSetChanged()
    }

}