package com.example.booksharingv2.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("opensearch")
    suspend fun searchBooks(
        @Query("title") title: String,
    ): BookExample // Todo データモデル定義後に変更

    @GET("opensearch")
    suspend fun searchBookFromIsbn(
        @Query("isbn") isbn: String,
    ): BookExample // Todo データモデル定義後に変更
}

data class BookExample(
    val title: String,
    val author: String,
    val imageLinks: String
)