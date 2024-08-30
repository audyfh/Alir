package com.example.alir.presentation.main.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alir.model.news.News
import com.example.alir.model.news.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repo: NewsRepo
) : ViewModel(){

    private val _news = MutableStateFlow<List<News>>(emptyList())
    val news : StateFlow<List<News>> = _news

    init {
        getNews()
    }

    private fun getNews(){
        viewModelScope.launch {
            val newsList = repo.getNews()
            _news.value = newsList
        }
    }

}