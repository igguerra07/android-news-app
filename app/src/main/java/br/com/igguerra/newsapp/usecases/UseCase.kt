package br.com.igguerra.newsapp.usecases

interface UseCase<P, T> {
    suspend operator fun invoke(params: P): T
}