package br.com.igguerra.newsapp.repositories

import br.com.igguerra.newsapp.datasources.NewsRemoteDataSource
import br.com.igguerra.newsapp.entities.NewsResource
import br.com.igguerra.newsapp.entities.Resource
import br.com.igguerra.newsapp.models.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remote: NewsRemoteDataSource
) : NewsRepository {
    override suspend fun getNews(): Flow<Resource<NewsResource>> {
        return flow {
            try {
                val response = remote.getNews()
                val data = response.toDomainModel()
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Failure("Something went wrong..."))
            }
        }.flowOn(Dispatchers.IO)
    }
}

