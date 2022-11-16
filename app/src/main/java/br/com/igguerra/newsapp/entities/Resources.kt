package br.com.igguerra.newsapp.entities

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure(val message: String) : Resource<Nothing>()
}
