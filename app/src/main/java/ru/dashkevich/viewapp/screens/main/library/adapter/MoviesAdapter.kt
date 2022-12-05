package ru.dashkevich.viewapp.screens.main.library.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.data.api.movies.model.Film

class MoviesAdapter(private var films: List<Film> = emptyList())
    : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view){
        val vImage: ImageView = view.findViewById(R.id.film_poster)
        val vTitle: TextView = view.findViewById(R.id.film_title)
        val vRating: TextView = view.findViewById(R.id.film_rating)
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
                vTitle.text = nameRu
                vRating.text = rating
                vImage.load(posterUrl){
                    crossfade(true)
                    scale(Scale.FIT)
                }
            }
        }
    }

    override fun getItemCount(): Int = films.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(films: List<Film>) {
        this.films = films
        notifyDataSetChanged()
    }

}