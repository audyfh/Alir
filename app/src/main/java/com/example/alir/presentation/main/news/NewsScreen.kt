package com.example.alir.presentation.main.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alir.R
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.ui.theme.Biru
import com.example.alir.ui.theme.Jakarta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(

) {
    var selectedTab by remember {
        mutableStateOf(0)
    }

    val tabs = listOf(
        "Beranda",
        "Tersimpan"
    )
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(12.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Informasi dan Berita",
            fontFamily = Jakarta,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(top = 18.dp)
        )
        TextField(
            value = "",
            onValueChange ={},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(25.dp),
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray,
                ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search) ,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Cari",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.LightGray)

            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        TabRowComponent(
            tabs = tabs,
            contentScreens = listOf(
                { NewsBeranda()},
                { NewsSave()}
            )
        )
    }
}

@Composable
fun TabRowComponent(
    tabs: List<String>,
    contentScreens: List<@Composable () -> Unit>,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    selectedContentColor: Color = Biru,
    unselectedContentColor: Color = Color.LightGray,
    indicatorColor: Color = Biru,

) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = containerColor,
            contentColor = selectedContentColor,
            indicator = { tabPositions ->

                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = indicatorColor
                )
            }
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    modifier = Modifier.padding(all = 16.dp),
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(
                        text = tabTitle,
                        color = if (selectedTabIndex == index) selectedContentColor else unselectedContentColor
                    )

                }
            }
        }
        contentScreens.getOrNull(selectedTabIndex)?.invoke()
    }
}

@Preview(showBackground = true)
@Composable
private fun yuhu() {
    AlirTheme {
        NewsScreen()
    }
}