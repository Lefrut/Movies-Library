package ru.dashkevich.viewapp.common

interface EventHandler<T> {

    fun processingEvent(event: T)
}