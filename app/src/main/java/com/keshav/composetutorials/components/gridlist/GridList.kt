package com.keshav.composetutorials.components.gridlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keshav.composetutorials.model.BookItem
import com.keshav.composetutorials.ui.theme.ComposeTutorialsTheme
import com.keshav.composetutorials.utils.getGsonFromAssets

@ExperimentalFoundationApi
@Composable
fun GridList() {
    val context = LocalContext.current
    val books = context.getGsonFromAssets("books.json")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Grid Item")
        LazyVerticalGrid(cells = GridCells.Fixed(2), modifier = Modifier.padding(20.dp)) {
            items(books) { book ->
                GridListItem(book)
            }
        }
    }
}

@Composable
fun GridListItem(bookItem: BookItem) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column {


            Text(text = bookItem.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "by ${bookItem.authors}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun GridListPreview() {
    ComposeTutorialsTheme {
        GridList()
    }
}