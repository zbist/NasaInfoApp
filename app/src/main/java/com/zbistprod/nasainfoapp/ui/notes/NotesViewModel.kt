package com.zbistprod.nasainfoapp.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zbistprod.nasainfoapp.repository.IRepository
import com.zbistprod.nasainfoapp.repository.RepositoryImpl

class NotesViewModel : ViewModel() {

    private val repository: IRepository = RepositoryImpl

    private val _notesLiveData: MutableLiveData<MutableList<String>> = MutableLiveData()
    val notesLiveData: LiveData<MutableList<String>> = _notesLiveData

    fun fetchNotes() {
        _notesLiveData.value = repository.getNotes()
    }
}