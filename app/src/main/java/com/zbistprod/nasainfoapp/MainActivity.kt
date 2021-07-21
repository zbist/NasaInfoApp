package com.zbistprod.nasainfoapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zbistprod.nasainfoapp.ui.main.MainFragment
import com.zbistprod.nasainfoapp.utils.MainRouter
import com.zbistprod.nasainfoapp.utils.RouterHolder

class MainActivity : AppCompatActivity(), RouterHolder {

    override val mainRouter = MainRouter(this)

    companion object {
        const val APP_PREFERENCES = "mysettings"
        const val APP_PREFERENCES_THEME = "theme"
    }

    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        chekTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initBottomNavView()

        if (savedInstanceState == null) {
            mainRouter.openMain()
        }
    }

    private fun initBottomNavView() {
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {

                    R.id.main -> {
                        mainRouter.openMain()
                        true
                    }

                    R.id.earth -> {
                        mainRouter.openEarth()
                        true
                    }

                    R.id.moon -> {
                        mainRouter.openMoon()
                        true
                    }

                    else -> false
                }
            }
    }

    private fun chekTheme() {
        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        setTheme(R.style.DarkTheme_NasaInfoApp)

        if (settings.contains(APP_PREFERENCES_THEME)) {
            if (settings.getBoolean(APP_PREFERENCES_THEME, true)) {
                setTheme(R.style.DarkTheme_NasaInfoApp)
            } else {
                setTheme(R.style.Theme_NasaInfoApp)
            }
        }
    }

    private fun changeTheme() {
        settings.edit()
            .putBoolean(APP_PREFERENCES_THEME, !settings.getBoolean(APP_PREFERENCES_THEME, true))
            .apply()
        recreate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_theme -> {
                changeTheme()
            }

            R.id.open_notes -> {
                mainRouter.openNotes()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}