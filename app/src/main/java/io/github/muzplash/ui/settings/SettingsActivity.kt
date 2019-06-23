package io.github.muzplash.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import io.github.muzplash.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings_container, SettingsFragment())
                .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)
        }

        override fun onDisplayPreferenceDialog(preference: Preference?) {
            if (preference !is NumberPickerPreference) {
                super.onDisplayPreferenceDialog(preference)
            } else {
                val pickerFragment = NumberPickerPreference.NumberPickerPreferenceFragment.newInstance(preference)
                pickerFragment.setTargetFragment(this, 0)
                pickerFragment.show(fragmentManager!!, javaClass.simpleName)
            }
        }
    }
}