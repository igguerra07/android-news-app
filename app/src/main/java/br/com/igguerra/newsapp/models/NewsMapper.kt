package br.com.igguerra.newsapp.models

import br.com.igguerra.newsapp.entities.News
import br.com.igguerra.newsapp.entities.NewsResource


fun NewsResponseModel.toDomainModel(): NewsResource {
    return NewsResource(
        total = totalResults,
        news = articles.map { it.toDomainModel() }
    )
}

fun NewsModel.toDomainModel(): News {
    return News(
        url = url,
        title = title,
        image = urlToImage ?: "",
        author = author ?: "No author",
        content = content ?: "No content",
        description = description ?: "No description",
    )
}