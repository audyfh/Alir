package com.example.alir.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alir.R
import com.example.alir.presentation.common.CustomToast
import com.example.alir.presentation.common.TextFieldLog
import com.example.alir.presentation.common.TextFieldPW
import com.example.alir.ui.theme.AlirTheme
import com.example.alir.ui.theme.Biru

@Composable
fun RegisterScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigate : () -> Unit,
) {
    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var checkPass by remember {
        mutableStateOf("")
    }
    LaunchedEffect(loginState) {
        viewModel.handleLoginState(
            onSuccess = {
                navigate()
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
        Column(
            modifier = Modifier
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box (
                modifier = Modifier.size(150.dp)
            ){
                Image(
                    painter = painterResource(id = R.mipmap.ic_splash1_foreground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            TextFieldLog(value = email, onValueChange = { email = it })
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldPW(value = password, onValueChange = { password = it })
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldPW(value = checkPass, onValueChange = { checkPass = it })
            Spacer(modifier = Modifier.height(80.dp))
            Button(
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    viewModel.register(email, password, checkPass)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Biru
                ),
                modifier = Modifier
                    .height(80.dp)
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Daftar",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun asda() {
    AlirTheme {
        RegisterScreen(navigate = {})
    }
}