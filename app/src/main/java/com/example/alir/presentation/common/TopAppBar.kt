package com.example.alir.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alir.R
import com.example.alir.ui.theme.AlirTheme

@Composable
fun CustomTopAppBar() {
    Row (
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp)
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_topapp),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .fillMaxHeight()

        )
        Spacer(modifier = Modifier.weight(2f))
        Row (
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)

            )
            Image(
                painter = painterResource(id = R.drawable.ic_message),
                contentDescription = null,

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun test() {
    AlirTheme {
        CustomTopAppBar()
    }
}