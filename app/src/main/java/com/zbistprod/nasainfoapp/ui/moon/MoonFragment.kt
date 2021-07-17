package com.zbistprod.nasainfoapp.ui.moon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.zbistprod.nasainfoapp.R
import com.zbistprod.nasainfoapp.databinding.MoonFragmentBinding
import com.zbistprod.nasainfoapp.utils.viewBinding

class MoonFragment : Fragment(R.layout.moon_fragment) {

    companion object {
        fun newInstance() = MoonFragment()
    }

    private val viewModel: MoonViewModel by viewModels()
    private val binding: MoonFragmentBinding by viewBinding(MoonFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.moonImg)
            .load("https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/0/0/1.jpg")
            .into(binding.moonImg)
    }

}