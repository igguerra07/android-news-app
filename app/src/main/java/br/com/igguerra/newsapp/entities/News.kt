package br.com.igguerra.newsapp.entities

data class News(
    val url: String,
    val image: String,
    val title: String,
    val author: String,
    val content: String,
    val description: String,
)
