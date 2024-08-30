package com.example.alir.presentation.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.alir.R
import com.example.alir.model.lapor.Report
import com.example.alir.presentation.main.community.CommunityScreen
import com.example.alir.presentation.main.home.HomeScreen
import com.example.alir.presentation.main.home.ScaffoldSheet
import com.example.alir.presentation.main.lapor.LaporDetailScreen
import com.example.alir.presentation.main.lapor.LaporDoneSreen
import com.example.alir.presentation.main.lapor.LaporProgress
import com.example.alir.presentation.main.lapor.LaporScreen
import com.example.alir.presentation.main.lapor.LaporViewModel
import com.example.alir.presentation.main.news.NewsScreen
import com.example.alir.presentation.main.profile.ProfileScreen
import com.example.alir.presentation.navgraph.component.BottomNavBar
import com.example.alir.presentation.navgraph.component.BottomNavItem


@Composable
fun Navigator(

) {

    val bottomNavItem = remember {
        listOf(
            BottomNavItem(R.drawable.ic_home,"Beranda"),
            BottomNavItem(R.drawable.ic_komunitas,"Komunitas"),
            BottomNavItem(R.drawable.ic_lapor,"Lapor"),
            BottomNavItem(R.drawable.ic_berita,"Berita"),
            BottomNavItem(R.drawable.ic_profile,"Profil")

        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when(backStackState?.destination?.route){
        Route.HomeScreen.Route -> 0
        Route.CommunityScreen.Route -> 1
        Route.ReportScreen.Route -> 2
        Route.NewsScreen.Route -> 3
        Route.ProfileScreen.Route -> 4
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                items = bottomNavItem ,
                selected = selectedItem,
                onItemSelected = {index ->
                    when(index){
                        0 -> navigateToTab(navController,Route.HomeScreen.Route)
                        1 -> navigateToTab(navController,Route.CommunityScreen.Route)
                        2 -> navigateToTab(navController,Route.ReportScreen.Route)
                        3 -> navigateToTab(navController,Route.NewsScreen.Route)
                        4 -> navigateToTab(navController,Route.ProfileScreen.Route)
                    }
                }
            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.Route,
            modifier = Modifier.padding(bottom = bottomPadding)
            ) {
            composable(Route.HomeScreen.Route) {
                ScaffoldSheet()
            }
            composable(Route.ProfileScreen.Route){
                ProfileScreen()
            }
            composable(Route.ReportScreen.Route){
                val viewModel : LaporViewModel = hiltViewModel()
                val report = viewModel.reportValue
                LaporScreen(
                    navigateToReport = {navController.navigate(Route.ReportDetailScreen.Route)},
                    reports = report,
                    reportOnClick = {
                        navigateToDetail(
                            navController,
                            it
                        )
                    }
                )
            }
            composable(Route.NewsScreen.Route){
                NewsScreen()
            }
            composable(Route.ReportDetailScreen.Route){
                LaporDetailScreen (
                    navigateBack = { navController.navigate(Route.ReportScreen.Route) },
                    navigateDone = {navController.navigate(Route.ReportDoneScreen.Route)})
            }
            composable(Route.ReportProgressScreen.Route){
                navController.previousBackStackEntry?.savedStateHandle?.get<Report?>("report")
                    ?.let { 
                        LaporProgress(
                            report = it,
                            navigateBack = {navController.navigate(Route.ReportScreen.Route)}
                        )
                    }
            }
            composable(Route.ReportDoneScreen.Route){
                LaporDoneSreen(
                    navigateBack = { navController.navigate(Route.ReportScreen.Route) },
                    navigateProgress = { navController.navigate(Route.ReportScreen.Route) })
            }
            composable(Route.CommunityScreen.Route){
                CommunityScreen()
            }
        }
    }
}


private fun navigateToTab(
    navController: NavController,
    route:String
){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homescreen->
            popUpTo(homescreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetail(navController: NavController, report: Report){
    navController.currentBackStackEntry?.savedStateHandle?.set("report",report)
    navController.navigate(
        route = Route.ReportProgressScreen.Route
    )
}