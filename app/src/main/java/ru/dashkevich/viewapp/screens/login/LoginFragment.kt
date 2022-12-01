package ru.dashkevich.viewapp.screens.login

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.dashkevich.viewapp.R
import ru.dashkevich.viewapp.common.Binding
import ru.dashkevich.viewapp.data.repository.DataStoreRepository
import ru.dashkevich.viewapp.databinding.FragmentLoginBinding
import ru.dashkevich.viewapp.util.constants.USER_LOGIN
import ru.dashkevich.viewapp.util.constants.USER_PASSWORD
import ru.dashkevich.viewapp.util.constants.dataStore
import ru.dashkevich.viewapp.util.log.logE
import ru.dashkevich.viewapp.util.ui.toast

val REMEMBER_USER_KEY = booleanPreferencesKey("remember_user")


class LoginFragment : Fragment(), Binding<FragmentLoginBinding> {


    override var _binding: FragmentLoginBinding? = null
    lateinit var viewModel: LoginViewModel

    private var key = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            LoginViewModel.Companion.Factory(
                DataStoreRepository(requireContext().dataStore)
            )
        )[LoginViewModel::class.java]
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