package br.com.igguerra.newsapp.datasources

import br.com.igguerra.newsapp.models.NewsResponseModel

interface NewsRemoteDataSource {
    suspend fun getNews(): NewsResponseModel
}