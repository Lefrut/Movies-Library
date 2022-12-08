package ru.dashkevich.library.model.mvi

interface EventHandler<T> {

    fun processingEvent(event: T)
}