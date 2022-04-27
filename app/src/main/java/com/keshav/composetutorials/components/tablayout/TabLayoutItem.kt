package com.keshav.composetutorials.components.tablayout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabLayoutItem(val title:String, val icon: ImageVector, val screen:ComposableFun) {
    object Home: TabLayoutItem("Home", icon = Icons.Default.Home, screen = { HomeScreen()})
    object Cart: TabLayoutItem("Cart", icon = Icons.Default.ShoppingCart, screen = { CartScreen() })
    object Profile: TabLayoutItem("Profile", icon = Icons.Default.AccountCircle, screen = { ProfileScreen() })
}

typealias ComposableFun = @Composable ()->Unit