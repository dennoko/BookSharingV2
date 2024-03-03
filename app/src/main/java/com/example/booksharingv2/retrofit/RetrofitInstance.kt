package com.example.booksharingv2.retrofit

import retrofit2.Retrofit

object RetrofitInstance {
    private const val BASE_URL = "https://ndlsearch.ndl.go.jp/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Todo: カスタムコンバータの登録
            .build()
    }

    val bookApi: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
}