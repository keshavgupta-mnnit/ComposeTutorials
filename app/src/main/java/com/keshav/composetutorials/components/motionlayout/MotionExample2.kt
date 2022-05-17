package com.keshav.composetutorials.components.motionlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import com.keshav.composetutorials.R

@ExperimentalMotionApi
@ExperimentalMaterialApi
@Composable
fun CollapsableToolbar() {
    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val heightInPx = with(LocalDensity.current) { maxHeight.toPx() } // Get height of screen
        val connection = remember {
            object : NestedScrollConnection {

                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipingState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipingState.performDrag(delta).toOffset()
                }

                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity
                ): Velocity {
                    swipingState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipingState,
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        0f to SwipingStates.COLLAPSED,
                        heightInPx to SwipingStates.EXPANDED,
                    )
                )
                .nestedScroll(connection)
        ) {
            Column {
                MotionLayoutHeader(progress =  if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction else 1f - swipingState.progress.fraction) {
                    ScrollableContent()
                }
            }
        }
    }
}

enum class SwipingStates {
    EXPANDED,
    COLLAPSED
}

@Composable
internal fun ScrollableContent() {
    val list = listOf(1..20).flatten()

    LazyColumn(
        modifier = Modifier.padding(bottom = 56.dp)
    ) {
        items(
            items = list,
            itemContent = { id ->
                ScrollableContentItem(id = id.toString())
            }
        )
    }
}

@Composable
fun ScrollableContentItem(id: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Jetpack Compose $id",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h5
            )
        }
    }
}


@ExperimentalMotionApi
@Composable
fun MotionLayoutHeader(progress: Float, scrollableBody: @Composable () -> Unit) {
    MotionLayout(
        start = JsonConstraintSetStart(),
        end = JsonConstraintSetEnd(),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "poster",
            modifier = Modifier
                .layoutId("poster")
                .background(MaterialTheme.colors.primary),
            contentScale = ContentScale.FillWidth,
            alpha = 1f - progress
        )
        Text(
            text = "Make it Easy",
            modifier = Modifier
                .layoutId("title")
                .wrapContentHeight(),
            color = motionColor("title", "textColor"),
            fontSize = motionFontSize("title", "textSize"),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Box(
            Modifier
                .layoutId("content")
        ) {
            scrollableBody()
        }
    }
}

@Composable
private fun JsonConstraintSetStart() = ConstraintSet (""" {
	poster: { 
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
		top: ['poster', 'bottom', 16],
		start: ['parent', 'start', 16],
		custom: {
			textColor: "#000000", 
			textSize: 40
		}
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['title', 'bottom', 16],
	}
} """ )

@Composable
private fun JsonConstraintSetEnd() = ConstraintSet (""" {
	poster: { 
		width: "spread",
		height: 56,
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
		top: ['parent', 'top', 0],
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0], 
		bottom: ['poster', 'bottom', 0],
		custom: {
			textColor: "#ffffff",
			textSize: 20
        }
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['poster', 'bottom', 0],
	}
                  
} """)