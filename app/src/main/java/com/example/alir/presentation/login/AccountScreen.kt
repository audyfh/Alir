package com.example.alir.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alir.R
import com.example.alir.model.auth.Account
import com.example.alir.model.auth.repository.Resource
import com.example.alir.presentation.common.CustomTextField
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.ui.theme.Biru

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = hiltViewModel(),
    navigate: () -> Unit,
    navigateIfLogged: () -> Unit
) {

    val isProfileSaved by viewModel.isProfileSaved.collectAsState()
    val userProfile by viewModel.userProfileState.collectAsState()
    LaunchedEffect(Unit) {
        if (isProfileSaved){
            navigateIfLogged()
        }
    }
    LaunchedEffect(userProfile) {
        if (userProfile is Resource.Success){
            navigate()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        var nama by remember{
            mutableStateOf("")
        }
        var userName by remember {
            mutableStateOf("")
        }
        var bio by remember {
            mutableStateOf("")
        }
        var ttl by remember{
            mutableStateOf("")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier.clickable {

                    }
                )
            }
            Text(
                text = "Buat Profil Anda",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_updateprofile) ,
                contentDescription = null,
                alignment = Alignment.Center)
        }
        Text(
            text = "Nama",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 20.dp))
        CustomTextField(
            value = nama,
            onValueChange = {nama = it},
            placeHolder = "Nama"
        )
        Text(
            text = "Username",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 20.dp))
        CustomTextField(value = userName, onValueChange = {userName = it}, placeHolder ="Username")
        Text(
            text = "Bio",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 20.dp))
        CustomTextField(value = bio, onValueChange = {bio = it}, placeHolder = "Life's a change")
        Text(
            text = "Tanggal Lahir",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 20.dp))
        CustomTextField(value = ttl, onValueChange = {ttl = it}, placeHolder ="Januari" )
        Spacer(modifier = Modifier.weight(1f))
        val account = Account(
            name =  nama,
            userName = userName,
            bio = bio,
            ttl = ttl
        )
        Row (
            verticalAlignment = Alignment.Bottom
        ){
            Button(
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    viewModel.saveUser(account)
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Biru
                ),
                modifier = Modifier
                    .height(80.dp)
                    .padding(15.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Selanjutnya",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }

    }
}

