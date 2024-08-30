package com.example.alir.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.alir.presentation.login.AccountScreen
import com.example.alir.presentation.login.AccountViewModel
import com.example.alir.presentation.login.LoginScreen
import com.example.alir.presentation.login.RegisterScreen
import com.example.alir.presentation.onboarding.OnBoardingScreen

@Composable
fun NavGraph(
    startDestination: String,
    googleSignIn : () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination ) {
        composable(Route.OnBoarding.Route){
            OnBoardingScreen(
                navigateLogin = {navController.navigate(Route.LoginScreen.Route)},
                navigateRegister = {navController.navigate(Route.RegisterScreen.Route)}
                )
        }
        composable(Route.LoginScreen.Route){
            LoginScreen(
                navigateHome = { navController.navigate(Route.AccountScreen.Route) },
                googleSignIn = googleSignIn,
                navigateRegister = {navController.navigate(Route.RegisterScreen.Route)}
            )
        }
        composable(Route.RegisterScreen.Route){
            RegisterScreen(
                navigate = {navController.navigate(Route.LoginScreen.Route)}
            )
        }
        composable(Route.AccountScreen.Route){
            AccountScreen(
                navigate = {navController.navigate(Route.AppNavigator.Route)},
                navigateIfLogged = {navController.navigate(Route.AppNavigator.Route)}
            )
        }
        composable(Route.AppNavigator.Route){
            Navigator()
        }
    }
}