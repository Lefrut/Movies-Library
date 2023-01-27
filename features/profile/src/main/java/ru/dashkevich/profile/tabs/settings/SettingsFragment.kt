package ru.dashkevich.profile.tabs.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import ru.dashkevich.profile.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}