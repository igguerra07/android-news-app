package br.com.igguerra.newsapp.entities

data class NewsResource(
    val total: Int,
    val news: List<News>
)