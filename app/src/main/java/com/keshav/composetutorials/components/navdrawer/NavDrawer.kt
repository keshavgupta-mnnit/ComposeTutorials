package com.keshav.composetutorials.components.navdrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keshav.composetutorials.ui.theme.Purple500

@Composable
fun NavDrawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String, title: String) -> Unit
) {
    val screenList = listOf(
        NavScreens.NavDrawerScreens.NavHomeScreen,
        NavScreens.NavDrawerScreens.NavCartScreen,
        NavScreens.NavDrawerScreens.NavProfileScreen
    )
    Column(
        modifier
            .background(Purple500)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Heading",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Subheading",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
        Divider(color = Color.White, thickness = 1.dp)
        screenList.forEach { screen ->
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .clickable {
                        onDestinationClicked(screen.route, screen.title)
                    }
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = screen.icon, contentDescription = null, tint = Color.White)
                Text(
                    text = screen.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }

    }
}