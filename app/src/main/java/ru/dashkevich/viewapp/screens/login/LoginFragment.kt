package ru.dashkevich.viewapp.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.common.Binding
import ru.dashkevich.viewapp.databinding.FragmentLoginBinding
import ru.dashkevich.viewapp.util.constants.USER_LOGIN
import ru.dashkevich.viewapp.util.constants.USER_PASSWORD
import ru.dashkevich.viewapp.util.log.logE

class LoginFragment : Fragment(), Binding<FragmentLoginBinding> {

    override var _binding: FragmentLoginBinding? = null
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            findNavController().currentBackStackEntry?.savedStateHandle?.apply{
                getLiveData<String>(USER_LOGIN).observe(viewLifecycleOwner){ login ->
                    loginInput.setText(login)
                    logE("LoginFragment", "Login: $login")

                }
                getLiveData<String>(USER_PASSWORD).observe(viewLifecycleOwner){ password ->
                    passwordInput.setText(password)
                    logE("LoginFragment", "Password: $password")
                }
            }


            loginButton.setOnClickListener {
                loginClicked(loginInput.text.toString(), passwordInput.text.toString())
            }


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance() = LoginFragment()
    }

    private fun loginClicked(login: String = "", password: String = "") {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
            login = login,
            password = password
        )
        findNavController().navigate(action)

        /*findNavController().navigate(
            R.id.action_loginFragment_to_registerFragment,
            bundleOf(USER_LOGIN to login, USER_PASSWORD to password),
            navOptions {
                anim {
                    enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                    popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    popExit = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                }
            }
        )*/
    }

}