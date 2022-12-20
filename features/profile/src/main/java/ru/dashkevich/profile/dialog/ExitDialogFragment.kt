package ru.dashkevich.profile.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import ru.dashkevich.profile.R

class ExitDialogFragment() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Вы точно хотите выйти?")
            .setPositiveButton("Да") { _, _ ->
                //Не работает, потому что нет зависимости проекта в которох
                //находится навигационный граф, мы пытаемся прочитать ссылку
                //а ее не находим в наших графах
                val login = "https://ru.dashkevich.authorization/loginFragment"
                val unknown = "https://ru.dashkevich.unknown/unknown"

                //ru.dashkevich.viewapp:id/library
                val request = NavDeepLinkRequest.Builder
                    .fromUri(unknown.toUri())
                    .build()
                findNavController().navigate(request)
            }
            .setNegativeButton("Нет") { _, _ -> }
            .create()


    companion object {
        const val TAG = "ExitDialog"
    }

}