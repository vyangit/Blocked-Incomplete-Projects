package vyan.alwaysonwidget

import android.content.ContentResolver
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import vyan.alwaysonwidget.fragments.AlwaysOnToggleFragment
import vyan.alwaysonwidget.fragments.DeveloperSettingsInstructionsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val isDeveloperModeOn = Settings.Global.getString(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED) == "1"

        val activeFragment: Fragment = if (isDeveloperModeOn)
            AlwaysOnToggleFragment.getInstance() else
            DeveloperSettingsInstructionsFragment.getInstance()

        setFragment(activeFragment)
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_container, fragment)
        transaction.commit()
    }
}
