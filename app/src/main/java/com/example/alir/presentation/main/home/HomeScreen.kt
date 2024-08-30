package com.example.alir.presentation.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alir.presentation.common.CustomTopAppBar
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.example.alir.R
import com.example.alir.presentation.common.DonasiCard
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.ui.theme.Jakarta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val account by viewModel.accountState.collectAsState()
    val userName = account.data?.userName
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ){
        CustomTopAppBar()
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.home_bg_fix),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    Column (
                        modifier = Modifier.padding(16.dp)
                    ){
                        Text(
                            text = "Halo!",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                        userName?.let {
                            Text(
                                text = it,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Jakarta,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    MapboxMap(
                        modifier = Modifier.fillMaxHeight(),
                        mapViewportState = MapViewportState().apply {
                            setCameraOptions {
                                zoom(10.0)
                                center(Point.fromLngLat(	112.62650300, 	-7.98189400))
                                pitch(0.0)
                                bearing(0.0)
                            }
                        },
                    )
                }

            }
            Column (
                modifier = Modifier.padding( horizontal = 14.dp)
            ){
                Spacer(modifier = Modifier.height(80.dp))
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
            }
            
        }

    }
    
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSheet(
    viewModel: HomeViewModel = hiltViewModel()
) {
    BottomSheetScaffold(
        sheetContent = {
            DonasiSheet(viewModel = viewModel)
        },
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = BottomSheetDefaults.SheetPeekHeight,
        contentColor = Color.Transparent,
        sheetContainerColor = Color.White,
        sheetDragHandle = {BottomSheetDefaults.DragHandle()}
    ) {
        HomeScreen(viewModel)
    }

}

@Composable
fun DonasiSheet(
    viewModel: HomeViewModel
) {
    val donasi = viewModel.donasi.collectAsState().value
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Bantu Mereka di Donasi",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Lihat Semua",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(donasi){
                DonasiCard(
                    title = it.title,
                    terdampak = it.terdampak,
                    kebutuhan = it.kebutuhan,
                    author = it.author,
                    imgUrl = it.imageUrl
                )
            }
        }

    }

}


