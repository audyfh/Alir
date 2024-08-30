package com.example.alir.presentation.navgraph.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.alir.R
import com.example.alir.ui.theme.Biru
import com.example.alir.ui.theme.Putih

@Composable
fun BottomNavBar(
    items : List<BottomNavItem>,
    selected: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth().height(70.dp).clip(RoundedCornerShape(20.dp)),
        containerColor = Putih,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected ,
                onClick = { onItemSelected(index)},
                icon = {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            painter = painterResource(id = item.icon) ,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Biru,
                    selectedTextColor =  Biru,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }

}

data class BottomNavItem(
    @DrawableRes val icon: Int,
    val text: String
)