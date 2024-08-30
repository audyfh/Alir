package com.example.alir.model.news

interface NewsRepo {
    suspend fun getNews() : List<News>
}