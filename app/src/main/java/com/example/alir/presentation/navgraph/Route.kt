package com.example.alir.presentation.navgraph

sealed class Route(
    val Route:String
) {
    data object LoginScreen : Route("loginScreen")
    data object RegisterScreen : Route("registerScreen")
    data object HomeScreen : Route("homeScreen")
    data object ProfileScreen : Route("profileScreen")
    data object OnBoarding : Route("onboarding")
    data object AppStart : Route("appStart")
    data object AppNavigation : Route("appNavigation")
    data object AppNavigator : Route("appNavigator")
    data object ReportScreen : Route("reportScreen")
    data object CommunityScreen : Route("communityScreen")
    data object NewsScreen : Route("newsScreen")
    data object AccountScreen : Route("accountScreen")
    data object ReportDetailScreen : Route("reportDetailScreen")
    data object ReportDoneScreen : Route("reportDone")
    data object ReportProgressScreen : Route("reportProgress")
}