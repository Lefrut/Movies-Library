package ru.dashkevich.viewapp.screens.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.common.Binding
import ru.dashkevich.viewapp.data.repository.DataStoreRepository
import ru.dashkevich.viewapp.databinding.FragmentSplashBinding
import ru.dashkevich.viewapp.util.constants.dataStore
import ru.dashkevich.viewapp.util.log.logE

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
            logE("SplashScreen", "DataStore Worked: $rememberUser")
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}