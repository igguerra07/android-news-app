package br.com.igguerra.newsapp.datasources


import br.com.igguerra.newsapp.models.NewsResponseModel
import br.com.igguerra.newsapp.network.NewsApi
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val api: NewsApi
) : NewsRemoteDataSource {

    override suspend fun getNews(): NewsResponseModel {
        return api.getNews()
    }
}