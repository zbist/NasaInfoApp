package com.zbistprod.nasainfoapp.ui.earth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.zbistprod.nasainfoapp.BuildConfig
import com.zbistprod.nasainfoapp.R
import com.zbistprod.nasainfoapp.databinding.EarthFragmentBinding
import com.zbistprod.nasainfoapp.databinding.MoonFragmentBinding
import com.zbistprod.nasainfoapp.ui.moon.MoonViewModel
import com.zbistprod.nasainfoapp.utils.viewBinding

class EarthFragment : Fragment(R.layout.earth_fragment) {

    companion object {
        fun newInstance() = EarthFragment()
    }

    private val viewModel: MoonViewModel by viewModels()
    private val binding: EarthFragmentBinding by viewBinding(EarthFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.earthImg)
            .load("https://api.nasa.gov/EPIC/archive/natural/2019/05/30/png/epic_1b_20190530011359.png?api_key=${BuildConfig.NASA_API_KEY}")
            .into(binding.earthImg)
    }

}