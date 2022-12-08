package ru.dashkevich.library.model

interface EventHandler<T> {

    fun processingEvent(event: T)
}