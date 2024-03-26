package com.example.booksharingv2.view.screen

import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.booksharingv2.ui.theme.BookSharingV2Theme
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning


@Composable
fun MyPage() {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = ("My Page"))
        Button(onClick = {startRead(context = context)}) {
            Text(text = "Start Read Barcode")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPagePreview() {
    BookSharingV2Theme {
        MyPage()
    }
}

private fun startRead(context: Context) {
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            //TODO : ALL_FORMATSだと他の形式も読み込んでしまいそう
            Barcode.FORMAT_ALL_FORMATS
        )
        .enableAutoZoom()
        .build()
    val scanner = GmsBarcodeScanning.getClient(context, options)

    scanner.startScan()
        .addOnSuccessListener { barcode ->
            val rawValue: String? = barcode.rawValue
            Log.d("BarcodeTest", "成功しました : $rawValue")
            rawValue?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        .addOnCanceledListener {
            Log.d("BarcodeTest", "キャンセルしました")
        }
        .addOnFailureListener { exception ->
            Log.d("BarcodeTest", "失敗しました : ${exception.message}")
        }
}