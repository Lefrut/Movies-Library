package ru.dashkevich.utility.tools

interface EventHandler<T> {

    fun processingEvent(event: T)
}