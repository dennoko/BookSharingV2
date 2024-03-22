package com.example.booksharingv2.retrofit

import com.example.booksharingv2.model.xml_converter.XmlCustomConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitInstance {
    private const val BASE_URL = "https://ndlsearch.ndl.go.jp/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(XmlCustomConverterFactory())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    val bookApi: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
}