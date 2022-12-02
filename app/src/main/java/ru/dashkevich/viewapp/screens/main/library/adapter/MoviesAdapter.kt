package ru.dashkevich.viewapp.screens.main.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dashkevich.viewapp.R

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {


    class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 1

    fun updateFlowerCount(updatedFlowerCount: Int) {

    }

}