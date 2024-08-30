package com.example.alir.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alir.model.auth.repository.Resource
import com.example.alir.R
import com.example.alir.presentation.common.TextFieldLog
import com.example.alir.presentation.common.TextFieldPW
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.ui.theme.Biru
import com.example.alir.ui.theme.Hitam

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateHome : () -> Unit,
    googleSignIn: () -> Unit,
    navigateRegister: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    LaunchedEffect(loginState) {
        viewModel.handleLoginState(
            onSuccess = {
                navigateHome()
            },
            onError = { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        )
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
       Image(
           painter = painterResource(id = R.drawable.background_buat_audy),
           contentDescription = null,
           contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
       )
        Column (
            modifier = Modifier
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.mipmap.ic_splash1_foreground),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            TextFieldLog(value = email, onValueChange = {email = it} )
            TextFieldPW(value = password, onValueChange ={password = it} )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row( modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    BlueSwitchButton()
                    Text(
                        text = "Ingat Saya",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 25.dp)
                    )
                }

                Text(
                    text = "Lupa Sandi?",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(end = 30.dp)
                        .clickable {

                        }
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                shape = RoundedCornerShape(10.dp),
                onClick = {viewModel.login(email, password)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Biru
                ),
                modifier = Modifier
                    .height(80.dp)
                    .padding(15.dp)
                    .fillMaxWidth()) {
                Text(
                    text = "Masuk",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text ="Atau",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                )
            Spacer(modifier = Modifier.height(25.dp))
            LoginButton(icon = ImageVector.vectorResource(id = R.drawable.ic_google ), text = "Masuk dengan Google" , onClick = googleSignIn)

            Spacer(modifier = Modifier.height(10.dp))
            LoginButton(icon = ImageVector.vectorResource(id = R.drawable.ic_facebook), text = "Masuk dengan Facebook") {
                
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row (
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Belum punya akun?",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold,
                        color = Hitam)
                )
                Text(
                    text = "Daftar",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Biru,
                    modifier = Modifier.clickable {
                        navigateRegister()
                    }
                )
            }
        }
    }
}


fun Modifier.bottomShadow(shadow: Dp) =
    this
        .clip(GenericShape { size, _ ->
            lineTo(size.width, 0f)
            lineTo(size.width, Float.MAX_VALUE)
            lineTo(0f, Float.MAX_VALUE)
        })
        .shadow(shadow)

private fun Modifier.bottomElevation(): Modifier = this.then(Modifier.drawWithContent {
    val paddingPx = 8.dp.toPx()
    clipRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height + paddingPx
    ) {
        this@drawWithContent.drawContent()
    }
})

@Composable
fun BlueSwitchButton() {
    var isChecked by remember { mutableStateOf(false) }

    Box (
        modifier = Modifier
            .size(10.dp)
            .graphicsLayer(scaleX = 0.8f, scaleY = 0.8f)
    ){
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier.size(10.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Biru,
                uncheckedThumbColor = Biru,
                checkedTrackColor = Biru,
                uncheckedTrackColor = Color.White,
                uncheckedBorderColor = Biru
            )
        )
    }

}

@Composable
fun LoginButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp)
            .border(
                1.dp,
                Color.Gray,
                shape = RoundedCornerShape(10.dp)
            )
        ,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = Color.LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun tests() {
    AlirTheme {
        LoginScreen(navigateHome = { }, googleSignIn = { }) {

        }
    }
}

