package com.example.booksharingv2.view.ui_components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        val selectedTab = remember { mutableStateOf(0) }

        TabRow(
            selectedTabIndex = selectedTab.value,
            modifier = Modifier,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ) {
            Tab(
                selected = selectedTab.value == 0,
                onClick = {
                    clickedHome()
                    selectedTab.value = 0
                }
            ) {
                BottomBarItem(
                    modifier = Modifier.weight(1f),
                    imageVector = Icons.Default.Home
                )
            }

            Tab(
                selected = selectedTab.value == 1,
                onClick = {
                    clickedMyPage()
                    selectedTab.value = 1
                }
            ) {
                BottomBarItem(
                    modifier = Modifier.weight(1f),
                    imageVector = Icons.Default.Person
                )
            }

            Tab(
                selected = selectedTab.value == 2,
                onClick = {
                    clickedSetting()
                    selectedTab.value = 2
                }
            ) {
                BottomBarItem(
                    modifier = Modifier.weight(1f),
                    imageVector = Icons.Default.Settings
                )
            }
        }
    }
}