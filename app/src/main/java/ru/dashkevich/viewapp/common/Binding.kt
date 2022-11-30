package ru.dashkevich.viewapp.common

import ru.dashkevich.viewapp.databinding.FragmentSplashBinding

//TODO(Доработать для наследования)
interface Binding<T> {

    var _binding: T?

    val binding: T
        get() = _binding!!

}

