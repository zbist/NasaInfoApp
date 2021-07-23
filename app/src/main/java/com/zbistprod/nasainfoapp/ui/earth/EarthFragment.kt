package com.zbistprod.nasainfoapp.ui.earth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
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

    private var isExpanded = false

    private val viewModel: MoonViewModel by viewModels()
    private val binding: EarthFragmentBinding by viewBinding(EarthFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.earthImg)
            .load("https://api.nasa.gov/EPIC/archive/natural/2019/05/30/png/epic_1b_20190530011359.png?api_key=${BuildConfig.NASA_API_KEY}")
            .into(binding.earthImg)

        binding.earthImg.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                binding.root,
                TransitionSet().addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            val params: ViewGroup.LayoutParams = binding.earthImg.layoutParams
            params.height =
                if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            binding.earthImg.layoutParams = params
            binding.earthImg.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }
    }

}