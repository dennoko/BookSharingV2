package com.example.booksharingv2.view.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.booksharingv2.ui.theme.BookSharingV2Theme

@Composable
fun HomeScreen() {
    Text(text = ("Home Screen"))
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    BookSharingV2Theme {
        HomeScreen()
    }
}