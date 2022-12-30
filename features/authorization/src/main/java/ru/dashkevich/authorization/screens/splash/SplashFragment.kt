package ru.dashkevich.authorization.screens.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dashkevich.authorization.R
import ru.dashkevich.authorization.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val viewModel by viewModel<SplashViewModel>()
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val progressBar = binding.progressBar
        viewModel.readyNextScreen.observe(viewLifecycleOwner){ data ->
            progressBar.progress = data.progress
            if(data.progress >= 100 && data.dataTake){
                viewModel.rememberUser.value?.let { navigateNextScreen(rememberUser = it) }
            }
        }


    }

    private fun navigateNextScreen(rememberUser: Boolean){
        if(!rememberUser) {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }else{
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            //findNavController().navigate(ru.dashkevich.navigation.R.id.action_global_main)
            //findNavController().navigate(ru.dashkevich.navigation.R.id.global_action_main_nav_graph)
        }
    }


}