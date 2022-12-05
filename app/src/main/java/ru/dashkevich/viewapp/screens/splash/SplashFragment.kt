package ru.dashkevich.viewapp.screens.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.StartActivity
import ru.dashkevich.viewapp.common.Binding
import ru.dashkevich.viewapp.data.repository.DataStoreRepository
import ru.dashkevich.viewapp.databinding.FragmentSplashBinding
import ru.dashkevich.viewapp.util.constants.dataStore
import ru.dashkevich.viewapp.util.log.logE
import ru.dashkevich.viewapp.util.log.logI
import ru.dashkevich.viewapp.util.ui.toast

class SplashFragment : Fragment(), Binding<FragmentSplashBinding> {

    private lateinit var viewModel: SplashViewModel
    override var _binding: FragmentSplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            SplashViewModel.Companion.Factory(
                DataStoreRepository(requireContext().dataStore)
            )
        )[SplashViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}