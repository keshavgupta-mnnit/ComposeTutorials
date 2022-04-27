package com.keshav.composetutorials.components.navdrawer

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keshav.composetutorials.components.tablayout.HomeScreen
import com.keshav.composetutorials.ui.theme.Purple500
import com.keshav.composetutorials.utils.NavDrawerConstants
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val topBar: @Composable () -> Unit = {
        TopAppBar(
            title = { Text(text = "Navigation Drawer Example") },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = {
                    Toast.makeText(context, "Clicked Menu", Toast.LENGTH_LONG).show()
                }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                }
            },
            backgroundColor = Purple500,
            elevation = AppBarDefaults.TopAppBarElevation
        )
    }

    val drawer: @Composable () -> Unit = {
        NavDrawer { route, title ->
            scope.launch {
                scaffoldState.drawerState.close()
            }
            NavDrawerConstants.title = title
            Toast.makeText(context, title, Toast.LENGTH_LONG).show()
            navController.navigate(route)
        }
    }

    Scaffold(
        topBar = { topBar() },
        scaffoldState = scaffoldState,
        drawerContent = { drawer() },
        drawerGesturesEnabled = true
    ) {
        NavigationHost(navController)

    }

}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination =NavScreens.NavDrawerScreens.NavHomeScreen.route ){
        composable(NavScreens.NavDrawerScreens.NavHomeScreen.route){
            NDHomeScreen(NavDrawerConstants.title)
        }
    }
}

@Composable
fun NDHomeScreen(value: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = value,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontStyle = FontStyle.Italic
        )
    }
}