package br.com.igguerra.newsapp.network

import br.com.igguerra.newsapp.models.NewsResponseModel
import retrofit2.http.GET

interface NewsApi {
    @GET("top-headlines?country=br")
    suspend fun getNews(): NewsResponseModel
}