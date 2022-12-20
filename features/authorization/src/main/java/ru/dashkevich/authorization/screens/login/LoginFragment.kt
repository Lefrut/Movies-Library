package ru.dashkevich.authorization.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dashkevich.authorization.R
import ru.dashkevich.authorization.databinding.FragmentLoginBinding
import ru.dashkevich.utility.constants.USER_LOGIN
import ru.dashkevich.utility.constants.USER_PASSWORD
import ru.dashkevich.utility.log.logE

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    private var key = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        key = !key

        binding.apply {
            if (key) {
                when (val values = getRegisterValues()) {
                    null -> {
                        logE(
                            "LoginFragment",
                            "Не передается логин и пароль в login screen или это 1 заход"
                        )
                    }
                    else -> {
                        loginInput.setText(values.first)
                        passwordInput.setText(values.second)
                    }
                }
            }

            checkBoxRememberUser.setOnCheckedChangeListener() { _, isChecked ->
                viewModel.addOptionRememberUser(isChecked)
            }


            loginButton.setOnClickListener {
                loginClicked(loginInput.text.toString(), passwordInput.text.toString())
            }
        }
    }

    private fun loginClicked(login: String = "", password: String = "") {
        /*val action = LoginFragmentDirections
            .actionLoginFragmentToRegisterFragment(login, password)
        findNavController().navigate(action)*/
       findNavController().navigate(ru.dashkevich.navigation.R.id.action_global_main_nav_graph)
    }

    private fun getRegisterValues(): Pair<String, String>? {
        findNavController().currentBackStackEntry?.savedStateHandle?.apply {
            val login = get<String>(USER_LOGIN)
            val password = get<String>(USER_PASSWORD)
            return if (login != null && password != null) {
                Pair(login, password)
            } else {
                null
            }
        }
        return null
    }

}