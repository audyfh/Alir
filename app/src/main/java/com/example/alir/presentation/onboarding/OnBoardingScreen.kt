package com.example.alir.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alir.R
import com.example.alir.presentation.onboarding.component.OnBoardingPage
import com.example.alir.presentation.onboarding.component.PageIndicator
import com.example.alir.presentation.onboarding.component.pages
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.ui.theme.Biru
import com.example.alir.ui.theme.Putih
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navigateLogin: () -> Unit,
    navigateRegister:() -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember{
            derivedStateOf {
                when(pagerState.currentPage){
                    0 -> listOf("Lewati","")
                    1 -> listOf("Lewati","")
                    2 -> listOf("","")
                    else -> listOf("","")
                }
            }
        }
        HorizontalPager(state = pagerState) {
            OnBoardingPage(page = pages[it])
        }
        Spacer(modifier = Modifier.weight(1f))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .navigationBarsPadding(),

        ) {
            val scope = rememberCoroutineScope()
            if (buttonState.value[0].isEmpty()) {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = navigateLogin,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Biru
                    ),
                    modifier = Modifier
                        .height(80.dp)
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Masuk",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = navigateRegister,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Putih
                    ),
                    modifier = Modifier
                        .height(80.dp)
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Daftar",
                        style = MaterialTheme.typography.labelSmall,
                        color = Biru
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrowback),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage-1)
                            }
                        }
                    )
                    PageIndicator(
                        modifier = Modifier.width(52.dp),
                        pageSize = pages.size,
                        selectedPage = pagerState.currentPage
                    )
                    Text(
                        text = buttonState.value[0],
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = buttonState.value[0],
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(page = 2)
                            }
                        }
                    )
                    PageIndicator(
                        modifier = Modifier.width(52.dp),
                        pageSize = pages.size,
                        selectedPage = pagerState.currentPage
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage+1)
                            }
                        }
                    )
                }

            }
        }
        Spacer(modifier = Modifier.weight(0.2f))
    }
}

@Preview(showBackground = true)
@Composable
private fun Yeah() {
    AlirTheme {
        OnBoardingScreen(navigateRegister = {}, navigateLogin = {})
    }
}