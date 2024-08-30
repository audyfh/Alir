package com.example.alir

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.alir.presentation.login.LoginViewModel
import com.example.alir.presentation.navgraph.NavGraph
import com.example.alir.presentation.navgraph.Navigator
import com.example.alir.presentation.navgraph.Route
import com.example.alir.ui.theme.AlirTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                loginViewModel.splashCondition
            }
        }

        setContent {
            AlirTheme {
                Box(modifier = Modifier.background(color = Color.White)){
                    val startDestination = loginViewModel.startDestination
                  NavGraph(startDestination = startDestination, googleSignIn = {startGoogleSignIn()} )
                }
            }
        }
    }
    private fun startGoogleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let { loginViewModel.loginGoogle(it)}
            } catch (e: ApiException) {

            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}



