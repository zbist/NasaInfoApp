package com.zbistprod.nasainfoapp.utils

import androidx.appcompat.app.AppCompatActivity
import com.zbistprod.nasainfoapp.R
import com.zbistprod.nasainfoapp.ui.earth.EarthFragment
import com.zbistprod.nasainfoapp.ui.main.MainFragment
import com.zbistprod.nasainfoapp.ui.moon.MoonFragment
import com.zbistprod.nasainfoapp.ui.notes.NotesFragment

class MainRouter(private val activity: AppCompatActivity) {

    fun openMain() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container_for_fragment, MainFragment.newInstance()).commit()
    }

    fun openEarth() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container_for_fragment, EarthFragment.newInstance()).commit()
    }

    fun openMoon() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container_for_fragment, MoonFragment.newInstance()).commit()
    }

    fun openNotes() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container_for_fragment, NotesFragment.newInstance()).commit()
    }
}