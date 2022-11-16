package br.com.igguerra.newsapp.di

import br.com.igguerra.newsapp.datasources.NewsRemoteDataSource
import br.com.igguerra.newsapp.datasources.NewsRemoteDataSourceImpl
import br.com.igguerra.newsapp.repositories.NewsRepository
import br.com.igguerra.newsapp.repositories.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindsRemoteDataSource(remoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    fun bindsRepository(repositoryImpl: NewsRepositoryImpl): NewsRepository
}