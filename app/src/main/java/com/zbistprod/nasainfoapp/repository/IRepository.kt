package com.zbistprod.nasainfoapp.repository

import com.zbistprod.nasainfoapp.model.Apod

interface IRepository {

    fun getApod(callback: (result: RepositoryResult<Apod>) -> Unit)

}

sealed class RepositoryResult<T> {
    data class Success<T>(val value: T) : RepositoryResult<T>()
    data class Error<T>(val value: Throwable) : RepositoryResult<T>()
}
