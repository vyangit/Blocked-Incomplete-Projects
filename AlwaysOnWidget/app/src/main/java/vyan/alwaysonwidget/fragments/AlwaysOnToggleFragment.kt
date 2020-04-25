package vyan.alwaysonwidget.fragments

import android.content.res.ColorStateList
import android.os.BatteryManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vyan.alwaysonwidget.R

class AlwaysOnToggleFragment: Fragment() {
    private lateinit var toggleBtnView: FloatingActionButton
    private lateinit var indicatorTxtView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stay_awake_toggle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        indicatorTxtView = view.findViewById(R.id.fragment_stay_awake_toggle_indicator_text)
        toggleBtnView = view.findViewById(R.id.fragment_stay_awake_toggle_fab_main_toggle)
        toggleBtnView.setOnClickListener { toggleStayAwakeSetting() }
    }

    override fun onResume() {
        super.onResume()

        // Initialization of the toggle state
        val isStayAwakeSettingEnabled = Settings.Global.getString(
            requireContext().contentResolver,
            Settings.Global.STAY_ON_WHILE_PLUGGED_IN) == "1"

        updateStayAwakeToggleView(isStayAwakeSettingEnabled)
    }

    private fun toggleStayAwakeSetting() {
        synchronized(this) {
            val isEnabled = !toggleBtnView.isActivated
            updateStayAwakeToggleView(isEnabled)
        }
    }

    private fun updateStayAwakeToggleView(isSettingEnabled: Boolean) {
        if (isSettingEnabled) {
            toggleBtnView.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.colorEnabled, null))
            indicatorTxtView.text = resources.getString(R.string.stay_awake_is_enabled)
        } else {
            toggleBtnView.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.colorDisabled, null))
            indicatorTxtView.text = resources.getString(R.string.stay_awake_is_disabled)
        }

        Settings.Global.putInt(
            requireContext().contentResolver,
            Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
            if (isSettingEnabled) BatteryManager.BATTERY_PLUGGED_USB else 0)
    }

    companion object {
        fun getInstance() = AlwaysOnToggleFragment()
    }
}