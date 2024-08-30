package com.example.alir.presentation.main.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.alir.R
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.ui.theme.Biru
import com.example.alir.ui.theme.Putih

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsBeranda(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val news = viewModel.news.collectAsState().value
    val items = listOf(
        "Berita terbaru",
        "Untuk anda",
        "Krisis Air",
        "Sumber daya"
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            LazyRow {
                items(items) {
                    TabItems(text = it)
                }
            }
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.newspict1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Text(
                text = "Berita Terbaru",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.newspict2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        items(news) {
            NewsCard(
                title = it.title,
                date = it.date,
                author = it.author,
                urlImage = it.imageUrl
            )
        }
    }
}

@Composable
private fun TabItems(
    text: String
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Biru)

    ){
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = Putih,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun NewsCard(
    title: String,
    date : String,
    author: String,
    urlImage: String
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$date - $author",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(urlImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "News Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.newspict1),
                error = painterResource(R.drawable.newspict2)
            )

    }

}




@Preview(showBackground = true)
@Composable
private fun sss() {
    AlirTheme {
       NewsCard(title = "asdsa", date ="sadsa" , author = "asdasd", urlImage = "")
    }
}