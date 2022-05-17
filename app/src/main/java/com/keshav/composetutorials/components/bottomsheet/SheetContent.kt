package com.keshav.composetutorials.components.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SheetContent(
    heightFraction: Float = 0.8f,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        Modifier.fillMaxWidth()
            .fillMaxHeight(fraction = heightFraction)
    ) {
        content()
    }
}