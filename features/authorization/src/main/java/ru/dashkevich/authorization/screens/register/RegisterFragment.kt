package ru.dashkevich.authorization.screens.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.dashkevich.authorization.R
import ru.dashkevich.authorization.databinding.FragmentRegisterBinding
import ru.dashkevich.utility.constants.USER_LOGIN
import ru.dashkevich.utility.constants.USER_PASSWORD
import ru.dashkevich.utility.log.logE
import ru.dashkevich.utility.ui.toast
import ru.dashkevich.viewapp.screens.register.RegisterViewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private val auth: FirebaseAuth by lazy { Firebase.auth }
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
            emailInput.setText(navArgs.email)
            passwordInput.setText(navArgs.password)


            registerButton.setOnClickListener {
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()
                val copyPassword = copyPasswordInput.text.toString()
                if(password == copyPassword) registerClicked(email, password)
                else toast("Пароли не совпадают")
            }

            haveAccountText.setOnClickListener {
                haveAccountClicked()
            }
        }

    }

    override fun onStop() {
        super.onStop()
        findNavController().previousBackStackEntry
            ?.savedStateHandle?.apply {
                binding.apply {
                    set(USER_LOGIN, emailInput.text)
                    set(USER_PASSWORD, passwordInput.text)
                }
            }

    }


    private fun registerClicked(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(
                        requireContext(), "$user registered.",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun haveAccountClicked() {
        findNavController().popBackStack()
    }

}