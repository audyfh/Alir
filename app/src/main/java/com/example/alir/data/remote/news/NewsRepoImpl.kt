package com.example.alir.data.remote.news

import com.example.alir.model.news.News
import com.example.alir.model.news.NewsRepo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : NewsRepo{

    override suspend fun getNews(): List<News> {
        val newsList = mutableListOf<News>()
        val snapshot = firestore.collection("news").get().await()

        for (document in snapshot.documents){
            val news = document.toObject(News::class.java)
            news?.let {
                newsList.add(it)
            }
        }
        return newsList
    }
}