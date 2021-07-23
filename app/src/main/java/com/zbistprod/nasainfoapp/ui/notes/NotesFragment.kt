package com.zbistprod.nasainfoapp.ui.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.zbistprod.nasainfoapp.R
import com.zbistprod.nasainfoapp.databinding.MainFragmentBinding
import com.zbistprod.nasainfoapp.databinding.NotesFragmentBinding
import com.zbistprod.nasainfoapp.utils.viewBinding

class NotesFragment : Fragment(R.layout.notes_fragment) {

    companion object {
        fun newInstance() = NotesFragment()
    }

    private val viewModel: NotesViewModel by viewModels()
    private val binding: NotesFragmentBinding by viewBinding(NotesFragmentBinding::bind)
    private lateinit var adapter: NotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.fetchNotes()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NotesAdapter()
        binding.rv.adapter = adapter

        ItemTouchHelper(ItemTouchHelperCallback(adapter))
            .attachToRecyclerView(binding.rv)

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            adapter.listOfNotes = it as MutableList<String>
        }

        binding.fab.setOnClickListener {
            adapter.listOfNotes.add("new note with new text new note with new text new note with new text new note with new text new note with new text new note with new text new note with new text")
            adapter.notifyItemInserted(adapter.listOfNotes.size - 1)
        }
    }

}