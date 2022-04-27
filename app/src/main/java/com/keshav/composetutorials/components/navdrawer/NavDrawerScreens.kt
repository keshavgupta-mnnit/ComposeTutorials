package com.keshav.composetutorials.components.navdrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreens(val route: String, val title: String) {
    sealed class NavDrawerScreens(route: String, title: String, val icon: ImageVector) :
        NavScreens(route, title) {
        object NavHomeScreen : NavDrawerScreens("HomeScreen", "Home", Icons.Default.Home)
        object NavCartScreen :
            NavDrawerScreens("HomeScreen", "Cart", icon = Icons.Default.ShoppingCart)
        object NavProfileScreen :
            NavDrawerScreens("HomeScreen", "Profile", icon = Icons.Default.AccountCircle)

    }
}