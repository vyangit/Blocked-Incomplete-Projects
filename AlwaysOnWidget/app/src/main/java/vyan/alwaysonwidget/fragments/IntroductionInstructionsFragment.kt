package vyan.alwaysonwidget.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import vyan.alwaysonwidget.R

class DeveloperSettingsInstructionsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_developer_settings_instructions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.fragment_developer_settings_instructions_button_settings).setOnClickListener {
            onSettingsButton()
        }
    }

    private fun onSettingsButton() {
        startActivity(Intent(Settings.ACTION_DEVICE_INFO_SETTINGS))
    }

    companion object {
        fun getInstance() = DeveloperSettingsInstructionsFragment()
    }
}