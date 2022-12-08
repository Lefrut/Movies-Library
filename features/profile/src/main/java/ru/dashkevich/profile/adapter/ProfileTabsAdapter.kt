package ru.dashkevich.profile.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileTabsAdapter(
    fragment: Fragment,
    private val list: List<Pair<Fragment,String>>
    ) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position].first

}