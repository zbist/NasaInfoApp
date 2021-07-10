package com.zbistprod.nasainfoapp.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.Preference
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
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

    private val viewModel: MainViewModel by viewModels()
    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.getApod()
        }
        setHasOptionsMenu(true)
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
            binding.bottomSheet.bottomSheetDescriptionHeader.text = it.title
            binding.bottomSheet.bottomSheetDescription.text = it.explanation
            if (it.mediaType == "image") {
                Glide.with(binding.imageView)
                    .load(it.hdurl)
                    .into(binding.imageView)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_theme -> {
                (requireActivity() as MainActivity).changeTheme()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}