package br.com.igguerra.newsapp.usecases

import br.com.igguerra.newsapp.entities.NewsResource
import br.com.igguerra.newsapp.entities.Resource
import br.com.igguerra.newsapp.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) : UseCase<NoParams, Flow<Resource<NewsResource>>> {

    override suspend operator fun invoke(params: NoParams): Flow<Resource<NewsResource>> {
        return repository.getNews()
    }
}