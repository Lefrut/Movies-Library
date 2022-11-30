package ru.dashkevich.viewapp.common

import androidx.fragment.app.Fragment

@Deprecated("No use", ReplaceWith("requireActivity() as FragmentNavigator"))
fun Fragment.navigate(): FragmentNavigator  = requireActivity() as FragmentNavigator

@Deprecated("Fragment Navigator Deprecated")
interface FragmentNavigator {

    fun firstNavigation(fragment: Fragment)

    fun replaceNavigation(fragment: Fragment)

    fun goBack()
}