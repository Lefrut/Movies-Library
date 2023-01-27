package ru.dashkevich.profile

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.dashkevich.profile.adapter.ProfileTabsAdapter
import ru.dashkevich.profile.databinding.FragmentProfileBinding
import ru.dashkevich.profile.dialog.ExitDialogFragment
import ru.dashkevich.profile.tabs.saved.SavedFragment
import ru.dashkevich.profile.tabs.settings.SettingsFragment

@Suppress("SameParameterValue")
class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private lateinit var binding: FragmentProfileBinding
    private val currentUser by lazy {
        Firebase.auth.currentUser
    }

    private val onActivityImageResult: ActivityResultLauncher<Intent>

    init {
        onActivityImageResult = initRegisterForActivityResult(REQUEST_IMAGE_CAPTURE) { result ->
            val imageBitmap: Bitmap? = result.data?.getParcelableExtra(CAMERA_DATA)
            Toast.makeText(requireContext(), "$imageBitmap", Toast.LENGTH_SHORT).show()
            if (imageBitmap != null) binding.avatar.setImageBitmap(imageBitmap)
            else Toast.makeText(requireContext(), "Bitmap == null", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        val tabs = listOf(Pair(SavedFragment(), "Сохранено"), Pair(SettingsFragment(), "Настройки"))

        setHasOptionsMenu(true)
        binding.avatar.setImageResource(R.drawable.user_default_avatar)
        binding.email.text = currentUser?.email ?: "email no found"

        val profileTabsAdapter = ProfileTabsAdapter(this, tabs)
        binding.pager.adapter = profileTabsAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { itemTab, position ->
            itemTab.text = tabs[position].second
        }.attach()



        binding.avatar.setOnClickListener {
            val photoIntent = Intent(ACTION_IMAGE_CAPTURE)
            onActivityImageResult.launch(photoIntent)
        }
    }

    private fun initRegisterForActivityResult(
        resultCode: Int,
        deep: (ActivityResult) -> Unit
    ): ActivityResultLauncher<Intent> {

        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == resultCode) deep(result)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_m, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_item_profile -> {
                ExitDialogFragment()
                    .show(childFragmentManager, ExitDialogFragment.TAG)
            }
        }
        return true
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Библиотека фильмов"
        }
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        const val CAMERA_DATA = "data"
    }

}