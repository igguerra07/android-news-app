package br.com.igguerra.newsapp.ui.news

sealed class NewsUiState<out T> {
    object Initial : NewsUiState<Nothing>()
    object Loading : NewsUiState<Nothing>()
    data class Loaded<T>(val data: T) : NewsUiState<T>()
    data class Failure(val message: String) : NewsUiState<Nothing>()
}