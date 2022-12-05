package ru.dashkevich.viewapp.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dashkevich.viewapp.common.Binding
import ru.dashkevich.viewapp.databinding.FragmentLoginBinding
import ru.dashkevich.viewapp.util.constants.USER_LOGIN
import ru.dashkevich.viewapp.util.constants.USER_PASSWORD
import ru.dashkevich.viewapp.util.log.logE
import ru.dashkevich.viewapp.util.ui.toast

val REMEMBER_USER_KEY = booleanPreferencesKey("remember_user")


class LoginFragment : Fragment(), Binding<FragmentLoginBinding> {


    override var _binding: FragmentLoginBinding? = null
    private val viewModel by viewModel<LoginViewModel>()


    private var key = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            checkBoxRememberUser.setOnCheckedChangeListener(){ _, isChecked ->
                viewModel.addOptionRememberUser(isChecked)
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



    private fun loginClicked(login: String = "", password: String = "") {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
            login = login,
            password = password
        )
        findNavController().navigate(action)
    }

    private fun getRegisterValues(): Pair<String, String>? {
        var result: Pair<String, String>? = null
        val a = findNavController().currentBackStackEntry?.savedStateHandle?.apply {
            val login = get<String>(USER_LOGIN)
            val password = get<String>(USER_PASSWORD)
            if (login != null && password != null) {
                result = Pair(login, password)
            }
        }
        val login = a?.get<String>(USER_LOGIN)
        toast("bundle find: $a", requireContext())
        logE("LoginFragment", "Bundle findNav: ${a}")
        logE("LoginFragment", "Bundle login: $login")
        return result
    }

}