package com.zbistprod.nasainfoapp.ui.moon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.bumptech.glide.Glide
import com.zbistprod.nasainfoapp.R
import com.zbistprod.nasainfoapp.databinding.MoonFragmentBinding
import com.zbistprod.nasainfoapp.utils.viewBinding

class MoonFragment : Fragment(R.layout.moon_fragment) {

    companion object {
        fun newInstance() = MoonFragment()
    }

    private var isExpanded = false

    private val viewModel: MoonViewModel by viewModels()
    private val binding: MoonFragmentBinding by viewBinding(MoonFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.moonImg)
            .load("https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/0/0/1.jpg")
            .into(binding.moonImg)

        binding.moonImg.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                binding.root,
                TransitionSet().addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            val params: ViewGroup.LayoutParams = binding.moonImg.layoutParams
            params.height =
                if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            binding.moonImg.layoutParams = params
            binding.moonImg.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }
    }

}