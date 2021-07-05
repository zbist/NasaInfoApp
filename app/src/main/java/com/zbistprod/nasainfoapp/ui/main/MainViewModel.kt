package com.zbistprod.nasainfoapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zbistprod.nasainfoapp.model.Apod
import com.zbistprod.nasainfoapp.repository.IRepository
import com.zbistprod.nasainfoapp.repository.RepositoryImpl
import com.zbistprod.nasainfoapp.repository.RepositoryResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository: IRepository = RepositoryImpl

    private val _apodResponseLiveData: MutableLiveData<Apod> = MutableLiveData()
    val apodResponseLiveData: LiveData<Apod> = _apodResponseLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getApod() {

        repository.getApod {
            when (it) {

                is RepositoryResult.Success -> {
                    _apodResponseLiveData.postValue(it.value)
                }

                is RepositoryResult.Error -> {
                    _errorLiveData.postValue(it.value.message)
                }
            }

        }
    }
}