package com.zbistprod.nasainfoapp.ui.main

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.preference.Preference
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.zbistprod.nasainfoapp.MainActivity
import com.zbistprod.nasainfoapp.R
import com.zbistprod.nasainfoapp.databinding.MainFragmentBinding
import com.zbistprod.nasainfoapp.utils.viewBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var isExpanded = false

    private val viewModel: MainViewModel by viewModels()
    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.getApod()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomSheetBehavior(binding.bottomSheet.bottomSheetContainer)
        initObservers()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }


    }

    private fun initObservers() {

        viewModel.apodResponseLiveData.observe(viewLifecycleOwner) {

            val spannable = SpannableString(it.title)
            spannable.setSpan(
                StyleSpan(Typeface.ITALIC),
                0,
                spannable.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(Color.GREEN),
                0,
                spannable.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            binding.bottomSheet.bottomSheetDescriptionHeader.text = spannable
            binding.bottomSheet.bottomSheetDescription.text = it.explanation
            if (it.mediaType == "image") {
                Glide.with(binding.imageView)
                    .load(it.hdurl)
                    .into(binding.imageView)

                binding.imageView.setOnClickListener {
                    isExpanded = !isExpanded
                    TransitionManager.beginDelayedTransition(
                        binding.root,
                        TransitionSet().addTransition(ChangeBounds())
                            .addTransition(ChangeImageTransform())
                    )

                    val params: ViewGroup.LayoutParams = binding.imageView.layoutParams
                    params.height =
                        if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
                    binding.imageView.layoutParams = params
                    binding.imageView.scaleType =
                        if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
                }
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

}