package ru.dashkevich.viewapp.screens.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.dashkevich.viewapp.databinding.FragmentRegisterBinding
import ru.dashkevich.viewapp.utility.constants.USER_LOGIN
import ru.dashkevich.viewapp.utility.constants.USER_PASSWORD
import ru.dashkevich.viewapp.utility.log.logE


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private val navArgs by navArgs<RegisterFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loginInput.setText(navArgs.login)
            passwordInput.setText(navArgs.password)


            registerButton.setOnClickListener {

            }
        }

    }

    override fun onStop() {
        super.onStop()
        logE("RegisterFragment", "login = ${binding.loginInput.text}")
        logE("RegisterFragment", "password = ${binding.passwordInput.text}")
        findNavController().previousBackStackEntry
            ?.savedStateHandle
            ?.set(USER_LOGIN, binding.loginInput.text)
        findNavController().previousBackStackEntry
            ?.savedStateHandle
            ?.set(USER_PASSWORD, binding.passwordInput.text)
        logE("RegisterFragment", "findNavController: ${findNavController()}")
        logE("RegisterFragment", "destination: ${findNavController().previousBackStackEntry?.destination}")
        logE("RegisterFragment", "onStop")
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {

    }

}