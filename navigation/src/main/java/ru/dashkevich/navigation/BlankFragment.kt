package ru.dashkevich.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ru.dashkevich.navigation.databinding.FragmentBlankBinding


class BlankFragment : Fragment(R.layout.fragment_blank) {

    lateinit var binding: FragmentBlankBinding
    private var navigate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankBinding.bind(view)
        if(navigate){
            navigate = false
            findNavController().navigate(R.id.action_blankFragment_to_start_nav_graph)
        }

    }



}