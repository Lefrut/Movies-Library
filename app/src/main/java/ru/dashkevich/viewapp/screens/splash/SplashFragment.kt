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
import ru.dashkevich.viewapp.databinding.FragmentSplashBinding
import ru.dashkevich.viewapp.util.log.FRAGMENT
import ru.dashkevich.viewapp.util.log.logD

class SplashFragment : Fragment(), Binding<FragmentSplashBinding> {

    private lateinit var viewModel: SplashViewModel
    override var _binding: FragmentSplashBinding? = null

    companion object {
        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
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
        viewModel.progress.observe(viewLifecycleOwner) { progressNow ->
            progressBar.progress = progressNow
            if (progressBar.progress >= 100) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.clearData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}