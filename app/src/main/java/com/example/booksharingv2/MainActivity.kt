package com.example.booksharingv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booksharingv2.screen.HomeScreen
import com.example.booksharingv2.screen.MyPage
import com.example.booksharingv2.screen.SettingScreen
import com.example.booksharingv2.ui.theme.BookSharingV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            BookSharingV2Theme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    // Todo: BottomBar を実装する
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") { HomeScreen() }
                            composable("mypage") { MyPage() }
                            composable("setting") { SettingScreen() }
                        }
                    }
                }
            }
        }
    }
}

