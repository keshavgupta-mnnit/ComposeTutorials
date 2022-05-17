package com.keshav.composetutorials.components.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.keshav.composetutorials.R
import com.keshav.composetutorials.ui.theme.Purple500

@Composable
fun RowScope.RadioScreenSmall() {
    RadioLogoSmall(
        modifier = Modifier.padding(10.dp)
    )

    Text(
        text = "FM Title / Make it Easy",
        color = Color.White,
        style = MaterialTheme.typography.caption,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        modifier = Modifier
            .weight(1f)
            .padding(15.dp)
    )
    IconButton(
        onClick = {

        },
        modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 15.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
fun RadioLogoSmall(
    modifier: Modifier = Modifier,
    placeholder: Modifier = modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(1.dp, CircleShape)
            .background(
                color = Purple500,
                shape = CircleShape
            )
            .then(placeholder)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_radio),
            contentDescription = "Radio",
            modifier = Modifier
                .size(48.dp)
        )
    }
}