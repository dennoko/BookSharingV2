package com.example.booksharingv2.view.ui_components.navigation

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    Icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .then(modifier)
    ) {
        Icon()
    }
}