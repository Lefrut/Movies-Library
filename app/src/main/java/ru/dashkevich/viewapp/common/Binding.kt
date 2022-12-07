package ru.dashkevich.viewapp.common

//TODO(Доработать для наследования)
interface Binding<T> {

    var _binding: T?

    val binding: T
        get() = _binding!!

}

