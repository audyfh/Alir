package com.example.alir.presentation.main.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewsSave(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val news = viewModel.news.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(news){
                NewsCard(
                    title = it.title ,
                    date = it.date,
                    author = it.author,
                    urlImage = it.imageUrl)
            }
        }
    }
}