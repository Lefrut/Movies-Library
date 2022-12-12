package ru.dashkevich.library.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.dashkevich.domain.model.PresentedFilm

object FilmsComparator : DiffUtil.ItemCallback<PresentedFilm>() {
    override fun areItemsTheSame(
        oldItem: PresentedFilm, newItem: PresentedFilm
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PresentedFilm, newItem: PresentedFilm
    ): Boolean {
        return oldItem == newItem
    }
}
