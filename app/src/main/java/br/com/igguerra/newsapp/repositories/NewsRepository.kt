package br.com.igguerra.newsapp.repositories

import br.com.igguerra.newsapp.entities.NewsResource
import br.com.igguerra.newsapp.entities.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): Flow<Resource<NewsResource>>
}