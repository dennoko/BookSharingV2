package com.example.booksharingv2.view.ui_components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBottomBar(
    clickedHome: () -> Unit,
    clickedMyPage: () -> Unit,
    clickedSetting: () -> Unit
) {
    BottomAppBar(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        tonalElevation = 8.dp,
    ) {
        // Todo: BottomBar のアイテムを実装する
        BottomBarItem(
            modifier = Modifier.weight(1f),
            Icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            }
        ) {
            clickedHome()
        }

        BottomBarItem(
            modifier = Modifier.weight(1f),
            Icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            }
        ) {
            clickedMyPage()
        }

        BottomBarItem(
            modifier = Modifier.weight(1f),
            Icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            }
        ) {
            clickedSetting()
        }
    }
}