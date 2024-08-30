    package com.example.alir.presentation.main.lapor

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.ui.theme.Jakarta
import com.example.alir.R
import com.example.alir.model.lapor.Report
import com.example.alir.ui.theme.Biru
import com.example.alir.ui.theme.DarkBlue
import com.example.alir.ui.theme.LightAbu

    @Composable
fun LaporScreen(
    viewModel: LaporViewModel = hiltViewModel(),
    navigateToReport : () -> Unit,
    reports: List<Report>,
    reportOnClick: (Report) -> Unit
) {
    val reportt by viewModel.reports.collectAsState()
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text ="Lapor",
            fontWeight = FontWeight.Bold,
            fontFamily = Jakarta,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBlue)
                .padding(12.dp)
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = "Pelaporan Projek Air untuk",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Text(
                    text = "Kekeringan",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Text(
                    text = "Lakukan simulasi mitigasi dan evakuasi\n" +
                            "sesuai dengan kondisi geografi tempat\n " +
                            "tinggal anda dengan akselerasi AI dan AR",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_laporcamfix) ,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .weight(0.2f)
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {navigateToReport()},
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightAbu
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 16.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Icon(
                    painter = painterResource(id = R.drawable.ic_addlapor) ,
                    contentDescription = null ,
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Tambah Laporan",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Riwayat Laporan",
            style = MaterialTheme.typography.labelLarge,
            color = Color.LightGray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp)
        )
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(count = reportt.size){
                reportt[it]?.let {
                    ReportCard(report = it, onClick = {reportOnClick(it)})
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }

    }
}

    @Composable
    fun ReportCard(
        report: Report,
        onClick :(() -> Unit)? = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onClick?.invoke()
                }
        ) {
            Row (
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ){
                Text(
                    text = report.date,
                    style = MaterialTheme.typography.labelLarge,
                    color = Biru
                )
            }
            AsyncImage(
                model = report.photoUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
    }







