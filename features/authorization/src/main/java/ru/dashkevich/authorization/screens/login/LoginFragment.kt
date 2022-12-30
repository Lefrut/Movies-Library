package ru.dashkevich.authorization.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dashkevich.authorization.databinding.FragmentLoginBinding
import ru.dashkevich.utility.constants.USER_LOGIN
import ru.dashkevich.utility.constants.USER_PASSWORD
import ru.dashkevich.utility.log.logD
import ru.dashkevich.utility.log.logE

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val loginCoroutineScope by lazy { CoroutineScope(Dispatchers.Default) }

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
                        emailInput.setText(values.first)
                        passwordInput.setText(values.second)
                    }
                }
            }

            checkBoxRememberUser.setOnCheckedChangeListener() { _, isChecked ->
                viewModel.addOptionRememberUser(isChecked)
            }


            loginButton.setOnClickListener {
                loginClicked(
                    emailInput.text.toString(),
                    passwordInput.text.toString()
                )
            }

            registerText.setOnClickListener {
                registerTextClicked(
                    emailInput.text.toString(),
                    passwordInput.text.toString()
                )
            }

        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController()
                .navigate(ru.dashkevich.navigation.R.id.action_global_main)
        }
    }

    override fun onPause() {
        super.onPause()
        loginCoroutineScope.cancel()
    }

    private suspend fun signInUser(email: String, password: String): Result<AuthResult> {
        return kotlin.runCatching {
            val task = auth.signInWithEmailAndPassword(email, password)
            task.await()
        }
    }

    private fun registerTextClicked(email: String = "", password: String = "") {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
            email, password
        )
        findNavController().navigate(action)
    }


    private fun loginClicked(email: String = "", password: String = "") {
        var job: Job? = null
        job = loginCoroutineScope.launch {
            try {
                val result = signInUser(email, password)
                result.onSuccess {
                    withContext(Dispatchers.Main) {
                        findNavController().navigate(ru.dashkevich.navigation.R.id.action_global_main)
                    }
                }.onFailure {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "$it",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    logE("Fragment", it.toString())
                }
                job?.cancel()
            } finally {
            }
        }
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