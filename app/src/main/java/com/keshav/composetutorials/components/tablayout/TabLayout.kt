package com.keshav.composetutorials.components.tablayout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.*
import com.keshav.composetutorials.ui.theme.ComposeTutorialsTheme
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun MainTabScreen() {
    val tabList = listOf(TabLayoutItem.Home, TabLayoutItem.Cart, TabLayoutItem.Profile)
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(tabList, pagerState)
        TabContent(tabs = tabList, pagerState = pagerState)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TabLayoutItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.primary,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
        }
    ) {
        tabs.forEachIndexed { index, tabLayoutItem ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(text = tabLayoutItem.title)
                },
                icon = {
                    Icon(imageVector = tabLayoutItem.icon, contentDescription = null)
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabContent(tabs: List<TabLayoutItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun TabLayoutPreview() {
    ComposeTutorialsTheme {
        MainTabScreen()
    }
}
