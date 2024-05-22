package com.example.submissionfund2

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import com.example.submissionfund2.setting.SettingPreferences
import com.example.submissionfund2.setting.dataStore

class GithubTheme : Application() {

        override fun onCreate() {
            super.onCreate()
            val pref = SettingPreferences.getInstance(this.dataStore)
            pref.getThemeSetting().asLiveData().observeForever { isDarkModeActive: Boolean ->
                val mode = if (isDarkModeActive) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
                AppCompatDelegate.setDefaultNightMode(mode)
            }
        }
}