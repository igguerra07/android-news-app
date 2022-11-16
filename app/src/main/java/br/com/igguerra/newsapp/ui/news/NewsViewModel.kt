package br.com.igguerra.newsapp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.igguerra.newsapp.entities.NewsResource
import br.com.igguerra.newsapp.entities.Resource
import br.com.igguerra.newsapp.usecases.GetNewsUseCase
import br.com.igguerra.newsapp.usecases.NoParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNews: GetNewsUseCase
) : ViewModel() {

    private val _newsUiState = MutableStateFlow<NewsUiState<NewsResource>>(NewsUiState.Initial)
    val newsUiState: StateFlow<NewsUiState<NewsResource>> = _newsUiState

    fun getNews() {
        viewModelScope.launch {
            _newsUiState.value = NewsUiState.Loading
            getNews(NoParams)
                .catch { _newsUiState.value = NewsUiState.Failure("Unable to process data") }
                .collect {
                    _newsUiState.value = when (it) {
                        is Resource.Success -> NewsUiState.Loaded(it.data)
                        is Resource.Failure -> NewsUiState.Failure(it.message)
                    }
                }
        }
    }
}