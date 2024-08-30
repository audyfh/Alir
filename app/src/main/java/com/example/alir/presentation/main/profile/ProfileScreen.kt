package com.example.alir.presentation.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.R
import com.example.alir.ui.theme.Biru
import com.example.alir.ui.theme.LightAbu

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val account by viewModel.accountState.collectAsState()
    val userName = account.data?.userName
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Profil",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Akun Saya",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profilepic),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.width(30.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ){
                userName?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Normal User",
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightAbu
                    ),
                    modifier = Modifier
                        .height(30.dp)
                        .width(180.dp)
                ) {
                    Text(
                        text = "Belum terverifikasi",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Biru
                    ),
                    modifier = Modifier
                        .height(30.dp)
                        .width(180.dp)
                ) {
                    Text(
                        text = "Edit Profil",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
       TextUnderline(text = "Verifikasi Akun")
        TextUnderline(text = "Alamat saya")
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Pengaturan",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black)
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextUnderline(text = "Pengaturan Chat")
        TextUnderline(text = "Pengaturan Notifikasi")
        TextUnderline(text = "Pengaturan Privasi")
        TextUnderline(text = "Pengguna Diblokir")
        TextUnderline(text = "Bahasa/Language")
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Bantuan",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black)
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextUnderline(text = "Pusat Bantuan")
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "General",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Logout",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Red
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowtext),
                contentDescription = null )
        }
        HorizontalDivider(
            thickness = 1.dp
        )


    }
}

@Preview(showBackground = true)
@Composable
private fun ss() {
    AlirTheme {
        ProfileScreen()
    }
}

@Composable
fun TextUnderline(
    text: String
) {
    Spacer(modifier = Modifier.height(5.dp))
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrowtext),
            contentDescription = null )
    }
    HorizontalDivider(
        thickness = 1.dp
    )
}