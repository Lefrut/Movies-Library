package ru.dashkevich.profile.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.dashkevich.profile.R

class ExitDialogFragment() : DialogFragment() {



    private fun findRootNavController(): NavController {
        val host = requireActivity()
            .supportFragmentManager.findFragmentById(
                ru.dashkevich.navigation.R.id.fragment_container
            ) as NavHostFragment?

        return host?.navController ?: findNavController()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Вы точно хотите выйти?")
            .setPositiveButton("Да") { _, _ ->

                Firebase.auth.signOut()
                val login = "https://ru.dashkevich.authorization/loginFragment"
                val uri = NavDeepLinkRequest.Builder
                    .fromUri(login.toUri())
                    .build()

                findRootNavController().navigate(
                    request = uri,
                    navOptions = navOptions {
                        popUpTo(ru.dashkevich.navigation.R.id.main) {
                            inclusive = true
                        }
                    }
                )

            }
            .setNegativeButton("Нет") { _, _ -> }
            .create()


    companion object {
        const val TAG = "ExitDialog"
    }

}