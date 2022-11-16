package br.com.igguerra.newsapp.models

import com.google.gson.annotations.SerializedName

data class NewsResponseModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<NewsModel>
)
