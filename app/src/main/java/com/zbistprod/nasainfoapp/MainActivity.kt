package com.zbistprod.nasainfoapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zbistprod.nasainfoapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val APP_PREFERENCES = "mysettings"
        const val APP_PREFERENCES_THEME = "theme"
    }

    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        setTheme(R.style.DarkTheme_NasaInfoApp)

        if (settings.contains(APP_PREFERENCES_THEME)) {
            if (settings.getBoolean(APP_PREFERENCES_THEME, true)) {
                setTheme(R.style.DarkTheme_NasaInfoApp)
            } else {
                setTheme(R.style.Theme_NasaInfoApp)
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    fun changeTheme() {
        settings.edit()
            .putBoolean(APP_PREFERENCES_THEME, !settings.getBoolean(APP_PREFERENCES_THEME, true))
            .apply()
        recreate()
    }

}